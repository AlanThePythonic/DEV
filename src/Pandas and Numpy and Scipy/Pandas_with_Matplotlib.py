import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

# Series
data = pd.Series(data=np.random.randn(1000), index=np.arange(1000))
data = data.cumsum()

# DataFrame
data = pd.DataFrame(np.random.randn(1000, 4), index=np.arange(1000),
                    columns=list("ABCD"))
data = data.cumsum()

# Plot methods:
# Bar, Hist, box, kde, area, scatter, hexbin, pie
ax = data.plot.scatter(x='A', y='B', color='Red', label='Class 1')

# ax : Attached to the specified graph
data.plot.scatter(x='C', y='D', color='Green', label='Class 2', ax=ax)
plt.show()
