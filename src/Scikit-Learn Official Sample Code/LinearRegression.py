from sklearn import datasets
from sklearn.linear_model import LinearRegression
import matplotlib.pyplot as plt

loaded_data = datasets.load_boston()

data_X = loaded_data.data
data_Y = loaded_data.target

model = LinearRegression()
model.fit(data_X, data_Y)

print(model.predict(data_X[:4, :]))
print(data_Y[:4])
print(model.coef_)  # y = 0.1x + 0.3
print(model.intercept_)
print(model.get_params())

# R^2 Coefficient of Determnination, How similar between data and target
print(model.score(data_X, data_Y))

# X, Y = datasets.make_regression(
#     n_samples=100, n_features=1, n_targets=1, noise=0.001)

# plt.scatter(X, Y)
# plt.show()
