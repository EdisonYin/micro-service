import re
import urllib
from urllib import request

def getHtml(url):
    response = request.urlopen(url)
    html = response.read()
    return html
html = getHtml("""http://onairimg.mnet.com/data/od/mnettv/resources/js/mnettv/data/gen/produce48/trainee.js?v=153149""")

def Schedule(a,b,c):
       per = 100.0 * a * b / c
       if per>100:
           per = 100
           print('完成！')
       print('%.2f%%' % per)

reg = r'([photo][\S]*?(jpg))'
pattern = re.compile(reg)   # 查找数字
result1 = pattern.findall(html)
for i in result1:
    str = "".join(tuple(i))
    array = str.split('"')
    if (len(array) > 1):
        str2 = str.split('"')[2]
        if len(str2)> 20:
            print(str.split('"')[2][0:len(str2)-3])
            imgurl = str.split('"')[2][0:len(str2)-3]
            urllib.request.urlretrieve(imgurl,'F:\\py27\\%s.jpg' % 0,Schedule)
    else:
        str3 = str.split('"')[0]
        print (str.split('"')[0][0:len(str3)-3])
        imgurl = str.split('"')[0][0:len(str3)-3]
        urllib.request.urlretrieve(imgurl,'F:\\py27\\%s.jpg' % 0,Schedule)



