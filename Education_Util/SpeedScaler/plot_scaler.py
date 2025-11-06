import numpy as np
import matplotlib.pyplot as plt

def scaleOutput(x: np.ndarray, k: float = 2.0) -> np.ndarray:
    """
    入力 x（-2 ～ +2）に対して、
    出力 f(0) = 1.0、f(±2) = 0.4 を満たす滑らかな関数。
    k はカーブの「きつさ」：k = 1 → 線形、k > 1 →後半急落
    """
    abs_x = np.minimum(np.abs(x), 2.0)  # ±2以上を2にクランプ
    ratio = 1.0 - (abs_x / 2.0)         # 0 ≤ ratio ≤ 1
    return 0.4 + 0.6 * (ratio ** k)

# x を -2.0～+2.0 まで生成
x = np.linspace(-2.0, 2.0, 500)

# k の異なる値で描画
y_k1  = scaleOutput(x, k=1.0)   # 線形
y_k2  = scaleOutput(x, k=2.0)   # 二乗カーブ
y_k4  = scaleOutput(x, k=4.0)   # より後半急落

# scaler を掛けた値（ここでは例として k=2 を使用）
scaled_x = x * scaleOutput(x, k=2.0)  # 入力 x に対してスケーラー f(x) を掛けた出力

plt.figure(figsize=(6,4))
plt.plot(x, y_k1,  label="f(x), k=1.0",   color='blue')
plt.plot(x, y_k2,  label="f(x), k=2.0",   color='green')
plt.plot(x, y_k4,  label="f(x), k=4.0",   color='red')

plt.plot(x, scaled_x, label="x * f(x) (k=2)", color='magenta', linestyle='--')

plt.axhline(0, color='black', linewidth=0.5)
plt.axvline(0, color='black', linewidth=0.5)
plt.title("スケーラー関数とスケーラー適用後の出力")
plt.xlabel("入力 x (-2 ～ +2)")
plt.ylabel("出力")
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.show()