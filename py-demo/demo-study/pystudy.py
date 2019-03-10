counter=100 #赋值整型变量
miles=1000.0 #浮点型
name=  "John"#字符串
print (counter)
print (miles )
print (name )
#多个变量的赋值以及多个变量的输出
a,b,c= 1,53,"尹明的女仆"
d="I love you!XHJ"
print(a,b,c)
print(c[1])
#print(d[0])
#基本的输入输出流
size=int(input('请输入你的长度有多长（厘米）\n' ))
if size>15:
        print('你的XX好大啊！我好喜欢你的大XX啊！ ')
else:
        print(' 虽然你的XX不是非常大，但是已经足够我用了')
#python 的if语句
flag=False
if flag==False:
    print(d)
    flag=True
else:
   { print(a,b,c)
    
     }
#python的for循环
for letter in 'Python':     # 第一个实例
   print ('当前字母 :',letter)
fruits = ['尹明', '傅文杰',  '刘秉梁','小贱逼']
pms= ['第一位', '第二位',  '第三位','第四位']
   # names=['尹明', '傅文杰', '刘秉梁']
   #names="尹明 傅文杰 刘秉梁"
i=0   
for fruit in fruits:# 第二个实例
 print('年度寝室帅气排行榜位置:',pms[i])
 print('姓名 :', fruit)
 i=i+1 


#对于这两天内的python基本语法的学习我个人觉得python的基本语法块非常好 层次是很明显的
#数组的嵌套调用
array1s=[1,2,3,4,5,6,7,8,9]
array2s=[51,35,78,5,2,1,35,4,12,15]
for array1 in array1s:
 for array2 in array2s:
  if array1==array2:
   print('两个数组的相同元素有',array1)
#100以内的素数的输出hah
  i = 2
while(i < 100):

   j = 2
   while(j <= (i/j)):
      if not(i%j): break
      j = j + 1
   if (j > i/j) :
       print (i, " 是素数")
       i = i + 1





