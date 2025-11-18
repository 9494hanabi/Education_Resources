package frc.robot.Subsystems;

// ==================================================インポート==================================================
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveSubsystem extends SubsystemBase {
    private final PWMSparkMax leftFrontMotor  = new PWMSparkMax(1);
    private final PWMSparkMax leftBackMotor   = new PWMSparkMax(2);
    private final PWMSparkMax rightFrontMotor = new PWMSparkMax(3);
    private final PWMSparkMax rightBackMotor  = new PWMSparkMax(4);

    private final DifferentialDrive drive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);

    public DriveSubsystem() {
        leftFrontMotor.addFollower(leftBackMotor);
        rightFrontMotor.addFollower(rightBackMotor);
    }

    public void arcadeDrive(double moveSpeed, double rotateSpeed) {
        drive.arcadeDrive(moveSpeed, rotateSpeed);
    }

    @Override
    public void periodic() {
        
    }
}
