import pandas as pd
import numpy as np


def method():
    series = pd.Series(data=np.linspace(
        start=1, stop=10, num=5, dtype=np.int32))
    # Square and Filtering
    print(series.map(lambda x: x ** 2)[lambda x: x > 25])
    print()


def method2():
    df = pd.DataFrame(np.random.randint(
        100, size=20).reshape(4, 5), columns=list("ABCDE"))
    print(df[df.A > 50])


if __name__ == '__main__':
    method()
    method2()
