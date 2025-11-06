package frc.robot;

// ==================================================インポート==================================================
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.math.MathUtil;

// ==================================================ロボット==================================================
public class Robot extends TimedRobot {

  // ==================================================パーツ定義==================================================
  private final PWMSparkMax leftFrontMotor  = new PWMSparkMax(0);
  private final PWMSparkMax leftBackMotor   = new PWMSparkMax(1);
  private final PWMSparkMax rightFrontMotor = new PWMSparkMax(2);
  private final PWMSparkMax rightBackMotor  = new PWMSparkMax(3);

  private final XboxController controller = new XboxController(0);

  // ==================================================変数定義==================================================
  double drive      = 0.0;
  double rotation   = 0.0;

  double leftMotorSpeed  = 0.0;
  double rightMotorSpeed = 0.0;

  boolean leftMotorInvert  = false;
  boolean rightMotorInvert = true;

  public Robot() {
    
  }

  // 反転処理はここのタイミングで行う。Robot()で行うと、PWMSparkMaxに命令が届いて居ない可能性がある。
  @Override
  public void robotInit() {
    // 反転の処理を追加
    leftFrontMotor.setInverted(leftMotorInvert);
    leftBackMotor.setInverted(leftMotorInvert);
    rightFrontMotor.setInverted(rightMotorInvert);
    rightBackMotor.setInverted(rightMotorInvert);
  }

  // モーターの速度設定を抽象化
  public void setSpeed_LeftMotor(double speed) {
    leftFrontMotor.set(speed);
    leftBackMotor.set(speed);
  }

  public void setSpeed_RightMotor(double speed) {
    rightFrontMotor.set(speed);
    rightBackMotor.set(speed);
  }

  // スカラーのスケジューラ。 kは出力のカーブのきつさ。入力は +-2を想定している。
  // 仕様
  // 入力が大きくなるほど出力が非線形に小さくなる。
  // f(0)   -> 1
  // f(+-2) -> 0.4
  private double  speedScale(double x, double k) {
    double absX = Math.min(2.0, Math.abs(x));  // 入力を ±2 にクランプ
    double ratio = 1.0 - (absX / 2.0);
    return 0.4 + 0.6 * Math.pow(ratio, k);
  }

  @Override
  public void teleopPeriodic() {
    // スティックの入力情報を取得
    // 入力のデッドゾーン処理を追加
    drive    = MathUtil.applyDeadband(-controller.getLeftY(), 0.05);
    rotation = MathUtil.applyDeadband(controller.getRightX(), 0.05);

    drive    = Math.copySign(drive * drive, drive);
    rotation = Math.copySign(rotation * rotation, rotation);

    leftMotorSpeed  = drive + rotation;
    rightMotorSpeed = drive - rotation;

    
    setSpeed_LeftMotor(leftMotorSpeed * speedScale(leftMotorSpeed,  2));
    setSpeed_RightMotor(rightMotorSpeed * speedScale(rightMotorSpeed, 2));
  }
}