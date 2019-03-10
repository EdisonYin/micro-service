#!/usr/bin/env python
# -*- coding:utf-8 -*-
# -*- author:arron ni-*-
# python3抓取百度主页所有背景图片

import urllib.request,re,sys,os
def get_bing_backphoto():
    if (os.path.exists('baidu')== False):
        os.mkdir('baidu')
    for i in range(0,10):
        url = 'https://img6.bdstatic.com/img/image/smallpic/' + string[i] + '.jpg'
        html = urllib.request.urlopen(url).read()
        if html == 'null':
            print( 'open & read bing error!')
            sys.exit(-1)
        html = html.decode('utf-8')
        #html = html.replace('/az/','http://s.cn.bing.net/az/')
        reg = re.compile('"url":"(.*?)","urlbase"',re.S)
        text = re.findall(reg,html)
        #http://s.cn.bing.net/az/hprichbg/rb/LongJi_ZH-CN8658435963_1366x768.jpg
        for imgurl in text :
            right = imgurl.rindex('/')
            print(imgurl)
            name = imgurl.replace(imgurl[:right+1],'')
            savepath = 'baidu/'+ name
            urllib.request.urlretrieve(imgurl, savepath)
            print (name + ' save success!')
get_bing_backphoto()
