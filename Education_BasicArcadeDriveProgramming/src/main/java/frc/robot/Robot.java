package frc.robot;

// ==================================================インポート==================================================
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

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

  public Robot() {}

  @Override
  public void teleopPeriodic() {
    drive    = controller.getLeftY();
    rotation = controller.getRightX();
    
    leftMotorSpeed  = drive + rotation;
    rightMotorSpeed = drive - rotation;

    leftFrontMotor.set(leftMotorSpeed);
    leftBackMotor.set(leftMotorSpeed);

    rightFrontMotor.set(rightMotorSpeed);
    rightBackMotor.set(rightMotorSpeed);
  }
}