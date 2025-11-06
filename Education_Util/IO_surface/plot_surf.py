import numpy as np
import matplotlib.pyplot as plt

# --- speedScale() 関数をJava版と同等に定義 ---
def speedScale(x, k=2.0):
    abs_x = np.minimum(np.abs(x), 2.0)  # ±2 にクランプ
    ratio = 1.0 - (abs_x / 2.0)
    return 0.4 + 0.6 * (ratio ** k)

# --- joystick入力空間を作成 (rotation: X, drive: Y) ---
x = np.linspace(-1.0, 1.0, 100)   # rotation
y = np.linspace(-1.0, 1.0, 100)   # drive
X, Y = np.meshgrid(x, y)

# --- 左モーター出力の計算 (teleopPeriodicと同等) ---
leftMotorSpeed = Y + X
Z = leftMotorSpeed * speedScale(leftMotorSpeed, k=2.0)

# --- プロット設定 ---
fig = plt.figure(figsize=(8, 6))
ax = fig.add_subplot(111, projection='3d')

# サーフェス表示
surf = ax.plot_surface(X, Y, Z, cmap='plasma', linewidth=0, antialiased=True, alpha=0.9)

# --- 軸ラベル・タイトル ---
ax.set_xlabel('Rotation (RightStick Axis X)')
ax.set_ylabel('Drive (LeftStick Axis Y)')
ax.set_zlabel('OutputSpeed Z = speedScale(drive+rotation)')
ax.set_title('ジョイスティック入力空間におけるスケーラー出力')

fig.colorbar(surf, shrink=0.6, aspect=10, label="output_Z")
plt.tight_layout()
plt.show()