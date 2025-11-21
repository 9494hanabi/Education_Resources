package frc.robot;

// ==================================================インポート==================================================
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.TimedRobot;


public class Robot extends TimedRobot {

  // ==================================================パーツ定義==================================================
  private final PWMSparkMax leftFrontMotor  = new PWMSparkMax(0);
  private final PWMSparkMax leftBackMotor   = new PWMSparkMax(2);
  private final PWMSparkMax rightFrontMotor = new PWMSparkMax(1);
  private final PWMSparkMax rightBackMotor  = new PWMSparkMax(3);

  // ==================================================メソッド/クラスのインスタンス作成==================================================
  private final DifferentialDrive drive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);
  private final XboxController controller = new XboxController(0);

  public Robot() {
    leftFrontMotor.setInverted(false);
    rightFrontMotor.setInverted(true);
    leftFrontMotor.addFollower(leftBackMotor);
    rightFrontMotor.addFollower(rightBackMotor);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    double moveSpeed      = -controller.getLeftY();
    double rotationSpeed  = controller.getRightX();

    drive.arcadeDrive(moveSpeed, rotationSpeed);
  }
}
