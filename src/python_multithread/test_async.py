import asyncio


async def my_coroutine(future, task_name, seconds_to_sleep=3):
    print('{0} sleeping for: {1} seconds'.format(task_name, seconds_to_sleep))
    await asyncio.sleep(seconds_to_sleep)
    future.set_result('{0} is finished'.format(task_name))


def got_result(future):
    print(future.result())

loop = asyncio.get_event_loop()
future1 = asyncio.Future()
future2 = asyncio.Future()

tasks = [
    my_coroutine(future1, 'task1', 3),
    my_coroutine(future2, 'task2', 1)]


# Add a callback to be run when the future becomes done.
# The callback is called with a single argument - the future object. If
# the future is already done when this is called, the callback is
# scheduled with

future1.add_done_callback(got_result)
future2.add_done_callback(got_result)

loop.run_until_complete(asyncio.wait(tasks))
loop.close()
