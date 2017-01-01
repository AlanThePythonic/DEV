# Over-fitting : Over-looked to the accuracy, It will make a lot of noise
# and could not be a curve

from sklearn.learning_curve import learning_curve
from sklearn.datasets import load_digits
from sklearn.svm import SVC
import matplotlib.pyplot as plt
import numpy as np

digits = load_digits()
X = digits.data
Y = digits.target

train_sizes, train_loss, test_loss = learning_curve(SVC(gamma=0.001), X, Y, cv=10, scoring='mean_squared_error', train_sizes=[0.1, 0.25, 0.5, 0.75, 1])

train_loss_mean = -np.mean(train_loss, axis=1)
test_loss_mean = -np.mean(test_loss, axis=1)
