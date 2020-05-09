(function(window){
    function SoftMenu($trMenu){
    return new SoftMenu.prototype.init($trMenu);
    }
    SoftMenu.prototype={
    constructor:SoftMenu,
    menuArr:[],
    menuUseTime:[],
    TdList:[],
    currentNum:0,
    maxNum:100,
    init:function($trMenu){
        this.$trMenu=$trMenu;
    },
    deleteSide:function(){
         if(this.menuArr.length<7){
             //删除软件清单左右按钮
             this.$trMenu.find(".previous").hide();
             this.$trMenu.find(".next").hide();
             this.$trMenu.find('.not').remove()   
             for(i=0;i<this.TdList.length;i++){
             this.$trMenu.append(this.TdList[i]);
             }
         }else{        
          this.$trMenu.find(".previous").show();
             this.$trMenu.find(".next").show();
         this.$trMenu.find('.not').remove()    
            for(i=0;i<6;i++){
                this.$trMenu.find(".next").before(this.TdList[i]);
            }
            this.currentNum=0;
            this.maxNum=this.menuArr.length-6;

            
         }
    },
    getMenu:function(data){  
         this.menuArr = []
         this.menuUseTime = []
        $this=this;
        $.each(data,function(index,value){
            $this.menuArr[index]=value["softName"];  
            $this.menuUseTime[index]=value["useTime"];    
        });
        $this.setTd();
        $this.deleteSide();
    },
    getWebMenu:function(data){
    	  $this=this;
          this.menuArr = []
          this.menuUseTime = []
          $.each(data,function(index,value){
              $this.menuArr[index]=value["web_name"];  
              $this.menuUseTime[index]=value["count"];    
          });
          $this.setWebTd();
          $this.deleteSide();
    },
    setTd:function(){
        this.TdList = []
        var menuLen=this.menuArr.length;
        for(i=0;i<menuLen;i++){
        var $Td="<td class='not'>"+this.menuArr[i]+"<br>使用时长<br>"+this.menuUseTime[i]+"秒</td>";
        this.TdList.push($Td);
        }
    },
    setWebTd:function(){
        this.TdList = []
        var menuLen=this.menuArr.length;
        for(i=0;i<menuLen;i++){
        var $Td="<td class='not'>"+this.menuArr[i]+"<br>使用次数<br>"+this.menuUseTime[i]+"秒</td>";
        this.TdList.push($Td);
        }
    }
    }
    SoftMenu.prototype.init.prototype=SoftMenu.prototype;
    window.SoftMenu=SoftMenu; 
    })(window); 