import numpy as np
import matplotlib.pyplot as plt

def copy_sign_square(x: np.ndarray) -> np.ndarray:
    """
    x の符号を保持しつつ、|x| の二乗を戻す関数
    つまり Math.copySign(x*x, x) 相当
    """
    return np.sign(x) * (x * x)

# x を -1.0～+1.0 まで生成
x = np.linspace(-1.0, 1.0, 400)
y = copy_sign_square(x)

# グラフ描画
plt.figure(figsize=(6,4))
plt.plot(x, y, label="copySignSquare(x) = sign(x) * x^2", color='blue')
plt.axhline(0, color='black', linewidth=0.5)
plt.axvline(0, color='black', linewidth=0.5)
plt.title("Graph of sign(x) * x² (domain: -1 to 1)")
plt.xlabel("x")
plt.ylabel("output")
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.show()