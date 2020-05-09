//匿名函数(function() {})()避免函数体内外变量的冲突
//(js执行表达式顺序为圆括号里到圆括号外)
(function(window){
    //1.定义插件和参数
    function Menu($ulMenu){
    return new Menu.prototype.init($ulMenu);
    }
    //3.定义插件属性和方法
    Menu.prototype={
    constructor:Menu,
    menuArr:[],
    liList:[],
    currentNum:0,
    maxNum:100,
    init:function($ulMenu){
        this.$ulMenu=$ulMenu;
    },
    deleteSide:function(){
        //每次显示六个
         if(this.menuArr.length<7){
             //删除软件清单左右按钮
             $(".previous").remove();
             $(".next").remove();
             for(i=0;i<this.liList.length;i++){
             this.$ulMenu.append(this.liList[i]);
             }
         }else{            
            for(i=0;i<6;i++){
                //将菜单（六个为一组）放到下一页按钮的前面
                this.$ulMenu.find(".next").parent("li").before(this.liList[i]);
            }
            this.currentNum=0;
            this.maxNum=this.menuArr.length-6;
         }
    },
    getMenu:function($url,date){
        $this=this;
        $.ajax({
        	data:date,
        	contentType: 'application/json;charset=utf-8',
        	type:"post",
            url:$url,
            dataType:"json",
            async:false,
            success:function(data){  
                $this=$this;
                console.log(data);
                $.each(data,function(index,value){
                    $this.menuArr[index]=value;
                });
                $this.setLi();
                $this.deleteSide();
            },
            error:function(e){
                alert("读取菜单失败");
                //(e);
            }
       });
    },
    setLi:function(){
        var menuLen=this.menuArr.length;
        for(i=0;i<menuLen;i++){
        var $li="<li class=\"beClick\"><a href=\"#\">"+this.menuArr[i]+"</a></li>";
        this.liList.push($li);
        }
    }
    }
    Menu.prototype.init.prototype=Menu.prototype;
    window.Menu=Menu; //3.对外封装
    })(window); 