import pandas as pd
import numpy as np

df1 = pd.DataFrame(np.zeros((3, 4)), dtype=np.int32,
                   columns=['a', 'b', 'c', 'd'])

df2 = pd.DataFrame(np.ones((3, 4)), dtype=np.int32,
                   columns=['a', 'b', 'c', 'd'])

print(df1)
print(" ------------------------ ")
print(df2)
print(" ------------------------ ")
print(pd.concat([df1, df2], axis=0).reset_index(drop=True))
print(" ------------------------ ")
print(df1.append(df2, ignore_index=True))
print(" ------------------------ ")
ser1 = pd.Series([1, 2, 3, 4])
print(df1.append(ser1, ignore_index=True))
