#-*- coding: UTF-8 -*-

import urllib.request
from http.cookiejar import CookieJar

import json

url = 'http://www.baidu.com'
req_dict = {'k': 'v'}

cj = CookieJar()
handler = urllib.request.HTTPCookieProcessor(cj)
opener = urllib.request.build_opener(handler)

req_json = json.dumps(req_dict)
req_post = req_json.encode('utf-8')
headers = {}
#headers['Content-Type'] = 'application/json'
req = urllib.request.Request(url=url, data=req_post, headers=headers)

#urllib.request.install_opener(opener)
#res = urllib.request.urlopen(req)
# 或
res = opener.open(req)

res = res.read().decode('utf-8')
print(res)