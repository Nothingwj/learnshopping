## git基础配置
#### 配置用户名
##### git config --global user.name "你的用户名"
#### 配置邮箱
##### git config --global user.email "你的邮箱"
#### 编码配置
##### 避免git gui中的中文乱码
###### git config --global gui.encoding utf-8
##### 避免git status显示的中文文件名乱码
###### git config --global core.quotepath off
#### 其他
##### git config --global core.ignorecase false
## git ssh key pair配置
#### 在git bash命令行窗口中输入：ssh-keygen -t rsa -C "你的邮箱"
#### 然后一路回车，生成ssh key pair
#### 在用户目录下生成.ssh文件夹，找到公钥和私钥：id_rsa id_rsa.pub
#### 将公钥的内容复制
#### 进入github网站，将公钥添加进去
## git验证
#### 执行git --version出现版本信息，安装成功
## git常用命令
#### git init 创建本地仓库
#### git add 添加到暂存区
#### git commit -m "描述" 提交到本地仓库
#### git status 检查工作区文件状态
#### git log 查看提交committed
#### git reset --hard committid 版本回退
#### git branch 查看分支
#### git checkout -b dev 创建并切换到dev分支
#### git checkout 分支名 切换分支
#### git pull 拉取
#### git push -u origin master 提交
#### git merge branch 分支合并
## github上创建一个项目
#### 关联
##### git remote add origin "远程仓库地址"
#### 第一次向远程仓库推送
##### git push -u -f origin master
#### 以后提交到远程
##### git push origin master
## 企业项目开发模式
#### 项目采用：分支开发，主干发布
##### 创建分支：git checkout -b v1.0 origin/master
##### 将分支推送到远程：git push origin HEAD -u
##### 检查远程，发现多了v1.0分支
#### 项目提交到github
##### .gitignore文件：告诉git哪些文件不需要添加到版本管理中
##### 忽略规则：
###### *.a *忽视所有.a结尾的文件
###### ！lib.a 但lib.a除外
###### /TODO 仅仅忽略项目根目录下的TODO文件，不包括subdir/TODO
###### build/ 忽略build/目录下的所有文件
###### doc/*.txt 会忽略doc/notes.txt 但不包括doc/server/arch.txt
##### git add . 提交所有
## 远程分支合并
#### 远程合并dev分支
##### git checkout dev 切换到dev分支上
##### git pull origin dev 拉取远程dev上的内容
##### git checkout master 切换到master分支上
##### git merge dev 合并dev分支
##### git push origin master 推送到远程master分支上
