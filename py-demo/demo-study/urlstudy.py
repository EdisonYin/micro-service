#encoding:UTF-8 
import urllib.request


def getdata():#读取华东交通大学官网的基本信息
    url="http://www.ecjtu.jx.cn/p357c188/list.htm"
    data=urllib.request.urlopen(url).read()
    z_data=data.decode('UTF-8')
    
    print(z_data)
   
def savedata():#读取百度的网页信息
    url="http://www.baidu.com"
    data=urllib.request.urlopen(url).read()
    h_data=data.decode('UTF-8')
    #name = url.replace(url[:right+1],'')
    #savepath = 'photos/'+ name
    #urllib.request.urlretrieve(imgurl, savepath)
    print(h_data)
#savedata()
def binnaryconversion(x):#将一个十进制的数转换为二进制的数
    binnary=[0,0,0,0,0,0,0,0]
    base=[128,64,32,16,8,4,2,1]
    while(x!=0):
      for index in range(len(base)):
       if(base[index]>x and x>=base[index+1]):
        binnary[index+1]=1
        print(x)
        x=x-base[index+1]
        print(x)
        if(x<1):
            break
       
    return binnary 
     
def operate():
    a=60
    b=13
    print(a&b)
    print(a|b)
    print(a^b)
    print(~a)

   
#savedata()

print(binnaryconversion(98))

    




