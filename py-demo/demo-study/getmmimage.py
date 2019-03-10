# -*— coding:utf-8 -*-
import urllib.request
import urllib.response
import random
import bs4 as bs
import os
import sys
from PyQt5.QtWebEngineWidgets import QWebEnginePage
from PyQt5.QtWidgets import QApplication
from PyQt5.QtCore import QUrl


class MyWebBrowser(QWebEnginePage):
    app = None
    # 类变量 QApplication
    # 实际测试时，若调用了多个MyWebBrowser对象（有先后顺序的调用）
    # 比如现在某些页面上，获取了所有包含图片的页面链接，再去打开这些链接上抓取图片
    # 容易在这一步 super().__init__() 异常崩溃
    # 可能是在 QApplication.quit()调用时，出现了资源释放异常
    # 改成类变量控制后，没有出现崩溃现象，这个还需要再测试测试

    def __init__(self):
        if MyWebBrowser.app is None:
            MyWebBrowser.app = QApplication(sys.argv)
        # self.app = QApplication(sys.argv)
        # print("DownloadDynamicPage")
        super().__init__()
        self.html = ''
        # 将加载完成信号与槽连接
        self.loadFinished.connect(self._on_load_finished)
        # print("DownloadDynamicPage Init")

    def downloadHtml(self, url):
        """
            将url传入，下载此url的完整HTML内容（包含js执行之后的内容）
            貌似5.10.1自带一个download函数
            这个在5.8.2上也是测试通过的
        :param url:
        :return: html
        """
        self.load(QUrl(url))
        print("\nDownloadDynamicPage", url)
        # self.app.exec_()
        # 函数会阻塞在这，直到网络加载完成，调用了quit()方法，然后就返回html
        MyWebBrowser.app.exec_()
        return self.html

    def _on_load_finished(self):
        """
            加载完成信号的槽
        :return:
        """
        self.html = self.toHtml(self.Callable)

    def Callable(self, html_str):
        """
            回调函数
        :param html_str:
        :return:
        """
        self.html = html_str
        MyWebBrowser.app.quit()
        # self.app.quit()

def useRequestMethod(url):
    """
        传统方法不能下载动态网页
    """
    response = urllib.request.urlopen(url)
    with open("download.html", "w+", encoding="utf-8") as f:
        f.write(response.read().decode("utf-8"))


def useWebEngineMethod(url):
    """
        使用PyQt5的网页组件下载完整的动态网页
    """

    webBrowser = MyWebBrowser()
    html = webBrowser.downloadHtml(url)
    with open("download_by_web_engine.html", "w+", encoding="utf-8") as f:
        f.write(html)
    return html


def getImgUrlList(html: str):
    """
        从网页中解析所需要的图片的url，存储进list中
    """
    # 使用html.parser解析
    soup = bs.BeautifulSoup(html, 'html.parser')
    # 按条件查找img标签
    pageOptionList = soup.find_all('img', class_='test')
    print(pageOptionList)
    imgUrlList = list()
    for pageOptionEle in pageOptionList:
        # 获取img标签的src中的url
        imgUrl = pageOptionEle.get("src", None)
        if imgUrl is None:
            continue
        imgUrlList.append(imgUrl)
    return imgUrlList


class DownloadUrl:
    """
        使用urllib下载文件
    """
    # 用于绕过403错误的不同的浏览器标识
    userAgentList = [
        "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36",
        "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:30.0) Gecko/20100101 Firefox/30.0"
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/537.75.14",
        "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Win64; x64; Trident/6.0)",
        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36"
    ]

    @staticmethod
    def download(url, referer_url: str = ""):
        '''
            下载url所指文件，可一定程度上绕过403错误
        '''
        randomUserAgent = random.choice(DownloadUrl.userAgentList)

        request = urllib.request.Request(url)

        request.add_header("User-Agent", randomUserAgent)
        if "http" in url:
            host = url.split(r'/')[2]
        else:
            host = url.split(r'/')[0]
        # 请自行测试分割host方案，host最好也填写（别的博客中看到了填写此值，不太清楚是否真的有效）
        request.add_header("Host", host)
        request.add_header("GET", url)
        # referer_url 也最好填写，一般是本页或者Host，具体填入什么请根据被爬取的页面中Chrome浏览器Request Headers中的Referer值填入
        # 例如本博客的Referer为：https://blog.csdn.net/and_zj/article/details/80003543
        if referer_url != "":
            request.add_header("Referer", referer_url)
        response2 = urllib.request.urlopen(request)
        print(response2.getcode(), url)
        # 返回下载的所有内容
        return response2.read()

def downloadImage(imgUrlList: list, saveDir: str):
    """
        下载imgUrlList中所有的imgUrl，请注意，要组合完整的url
    """
    if not os.path.exists(saveDir):
        os.makedirs(saveDir)
    index = 0
    for imgUrl in imgUrlList:
        index = index + 1
        # 由于imgUrl可能不是完整的Url，所以需要组装成完整可访问的Url
        # 请根据实际情况，自己设计组装方案，或者，在上一步获取是，就组装好传递过来
        imgUrl = url.rsplit(r'/', 1)[0] + "/" + imgUrl.rsplit(r'/', 1)[1]
        # print(imgUrl)
        try:
            # referer_url 最好填写，参考Chrome中的referer结果填写
            # 这里是本地网页，所以没填
            content = DownloadUrl.download(imgUrl)
        except Exception as e:
            print(e, imgUrl)
            continue
        if b'403' in content:
            # 403并不会引起上述Exception
            print("403", imgUrl)
            continue
        # 对下载文件重命名：路径 + 序号 + 原文件名后缀
        file_name = saveDir + str(index) + '.' + imgUrl.split(r'.')[-1]
        # 图片文件以二进制格式写入
        f = open(file_name, 'wb')
        f.write(content)
        f.close()


if __name__ == '__main__':
    url = "http://produce48.mnet.com/pc/vote"
    # useRequestMethod(url)
    html = useWebEngineMethod(url)
    imgUrlList = getImgUrlList(html)
    print(imgUrlList)
    downloadImage(imgUrlList, "./downloadImage/")