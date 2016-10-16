# Create a closure method
def line_func():

    b = 6

    def line(x):
        return 5 * x + b

    return line


# Test closure function
b = 20
line_hl = line_func()
print("Test closure function : {0}".format(line_hl(3)))
print()

# Define a decorator


def decorator(FUNC):  # Receive a func object parameter

    # Encapsulate the code
    def hl_FUNC(a, b):
        print("input", a, b)
        return FUNC(a, b)
    return hl_FUNC  # Return a func object

# New encapsulated method


def preString(pre=''):  # New decorator with a parameter 'pre'
    # old decorator
    def decorator(FUNC):
        def hl_FUNC(a, b):
            print(pre + "input", a, b)
            return FUNC(a, b)
        return hl_FUNC
    return decorator  # Return to old decorator


@decorator  # Equals to decorator(squareSum)
def squareSum(a, b):
    return a ** 2 + b ** 2


@decorator
def squareDiff(a, b):
    return a ** 2 - b ** 2


print("Decorator without parameter - squareSum : {0}".format(squareSum(4, 2)))
print("Decorator without parameter - squareDiff : {0}".format(squareDiff(4, 2)))

print()

@preString('sum +')  # Pay attention
def squareSum2(a, b):
    return a ** 2 + b ** 2


@preString('diff -')
def squareDiff2(a, b):
    return a ** 2 - b ** 2


print("Decorator parameter - squareSum : {0}".format(squareSum2(4, 2)))
print("Decorator parameter - squareDiff : {0}".format(squareDiff2(4, 2)))


