package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.DriveSubsystem;
import frc.robot.Commands.DriveCommand;
import java.util.function.DoubleSupplier;

public class RobotContainer {
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();

  private final XboxController controller = new XboxController(0);

  public RobotContainer() {
    configureButtonBindings();

    DoubleSupplier moveSupplier = () -> {
      double val = -controller.getLeftY();
      return val;
    };
    DoubleSupplier rotateSupplier = () -> {
      double val = controller.getRightX();
      return val;
    };

    driveSubsystem.setDefaultCommand(
      new DriveCommand(driveSubsystem, moveSupplier, rotateSupplier)
    );
  }

  private void configureButtonBindings() {

  }

  public Command getAutonomousCommand() {
    return null;
  }
}
