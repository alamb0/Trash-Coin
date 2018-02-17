# importing the requests library
import requests
 
# defining the api-endpoint 
API_ENDPOINT = "http://106ada61.ngrok.io/user/post/2"
 
# your source code here
source_code = '''
print("Hello, world!")
a = 1
b = 2
print(a + b)
'''
 
# data to be sent to api
data = {}
 
# sending post request and saving response as response object
r = requests.post(url = API_ENDPOINT, data = data)
