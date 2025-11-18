package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.DriveSubsystem;
import java.util.function.DoubleSupplier;

public class DriveCommand extends Command {
    private final DriveSubsystem driveSubsystem;

    private final DoubleSupplier moveSupplier;
    private final DoubleSupplier rotateSupplier;

    public DriveCommand(
        DriveSubsystem driveSubsystem,
        DoubleSupplier moveSupplier,
        DoubleSupplier rotateSupplier) {
        this.driveSubsystem = driveSubsystem;
        this.moveSupplier   = moveSupplier;
        this.rotateSupplier = rotateSupplier;
        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        double moveSpeed   = moveSupplier.getAsDouble();
        double rotateSpeed = rotateSupplier.getAsDouble();
        driveSubsystem.arcadeDrive(moveSpeed, rotateSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
