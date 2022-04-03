from .enums import Status

orders = {}

def create_order(order_data):
    orders[len(orders) + 1] = order_data
    return order_data

def retrieve_orders():
    return orders
