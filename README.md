<h2>MigoCraft<br/>
<del>米戈♂克拉夫特</del> (大雾)</h2>
=========

[!WIP!] A Minecraft Mod, which looks like UgoCraft...?<br/>
[!制作中!] 一个MinecraftMod,疑似UgoCraft的仿制品...?


MigoCraft is a MCMod wrote by Szszss(a.k.a szszss), a modder and a tutorial-writer. MigoCraft is an open-source mod. It uses MIT License, which allows you not only study it, but also create a branch from it.<br/>
MigoCraft是一个MCMod,它的作者是szszss,一个Modder和Mod教程编写者.MigoCraft是一个开源Mod,因为它使用MIT许可证,因此你不但可以自由地阅读它的源代码,还能在你的作品里使用它的代码 - 只要你遵循MIT许可证的协议.


How to deploy it in your Eclipse<br/>
如何将项目部署到Eclipse中


Take it easy. Just clone it and link it as a source folder of your MCP project. However, since MigoCraft is a coremod, you have to copy "MigoCraft.jar" to your mcp\jars\mods folder (In another word, your "gameDir" folder).<br/>
However, you have to add some codes in your minecraft of MCP project. Add<br/>
<i>public Vec3 gravitySource = Vec3.createVectorHelper(0d, -1d, 0d);</i><br/>
in your Entity class.<br/>
很简单,只需Clone这个源并将它设为你的Minecraft项目的源代码目录之一即可.不过,由于MigoCraft是一个Coremod,所以你还得把MigoCraft.jar拷贝到你的MCP目录下的jars\mods目录里(换句话说,就是你的"gameDir"设置的目录). <br/>
然而,你还需要在你的MCP的minecraft项目中添加一行代码.在你的Entity类中添加:<br/>
<i>public Vec3 gravitySource = Vec3.createVectorHelper(0d, -1d, 0d);</i><br/>
