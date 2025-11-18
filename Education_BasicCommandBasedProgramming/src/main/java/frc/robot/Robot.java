package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer robotContainer;

  @Override
  public void robotInit() {
    // RobotContainer を生成してサブシステム・コマンドの設定を行う
    robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    // コマンドベースフレームワークのスケジューラを毎周期実行
    CommandScheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    // 自律モードが始まったとき呼ばれる
    m_autonomousCommand = robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void teleopInit() {
    // 手動モードが始まったとき呼ばれる
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void disabledInit() {
    // 無効モードに入ったとき呼ばれる
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void testInit() {
    // テストモードに入ったとき呼ばれる
    CommandScheduler.getInstance().cancelAll();
  }
}