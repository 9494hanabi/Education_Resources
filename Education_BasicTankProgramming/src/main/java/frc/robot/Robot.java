package frc.robot;
// ===============================インポート===============================
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

// ===============================ロボット===============================
public class Robot extends TimedRobot {
  
  // ===============================パーツ定義===============================
  private final XboxController controller = new XboxController(0);

  private final PWMSparkMax leftMotor   = new PWMSparkMax(0);
  private final PWMSparkMax rightMotor  = new PWMSparkMax(1);

  // ===============================変数定義===============================
  double left_Y   = 0;
  double right_Y  = 0;

  public Robot() {
    
  }

  @Override
  public void teleopPeriodic() {
    left_Y  = controller.getLeftY();
    right_Y = controller.getRightY();

    leftMotor.set(left_Y);
    rightMotor.set(right_Y);
  }
}