/**
 * Created by jyson on 2016. 8. 1..
 */



//var serverIp = 'http://hangeulbotelasticbeans-env.ap-northeast-2.elasticbeanstalk.com';
var serverIp = 'http://192.168.0.146:8888';

var deviceWidth = document.getElementById('contentHeight').offsetWidth;
var profileInContentDIVWidth = document.getElementById('profileInContentDIV').offsetWidth;
var deviceHeight = document.getElementById('contentHeight').offsetHeight;
//var deviceHeight = document.body.offsetHeight;

var DefaultGame = {};
DefaultGame.Boot = function(game){};
DefaultGame.Boot.prototype = {
    preload : function(){
        //추후 로딩바 삽입
    },
    create : function(){
        // set scale options
        this.input.maxPointers = 1;
        this.scale.scaleMode = Phaser.ScaleManager.SHOW_ALL;
        this.scale.pageAlignHorizontally = true;
        this.scale.pageAlignVertically = true;
        this.state.start('Preloader');

        // define width and height of the game
        DefaultGame.GAME_WIDTH = deviceWidth;
        DefaultGame.GAME_HEIGHT = deviceHeight;

        //음악을 angularJS 에 로딩시킨다
        loadingMusic();

    }
}
DefaultGame.Preloader = function(game){



};
DefaultGame.Preloader.prototype = {
    preload: function(){
        this.stage.backgroundColor = '#B4D9E7';
        this.load.crossOrigin = 'anonymous';
        this.load.image('background', serverIp+'/static/contentsResources/venezia2/bug_bg.jpg');
        this.load.image('background2', serverIp+'/static/contentsResources/venezia2/final_image.png');
        this.load.image('flower1',serverIp+'/static/contentsResources/venezia2/flower1.png');
        this.load.image('flower2',serverIp+'/static/contentsResources/venezia2/flower2.png');
        this.load.image('tobi_right',serverIp+'/static/contentsResources/venezia2/tobi_right.png');
        this.load.image('tobi_wrong',serverIp+'/static/contentsResources/venezia2/tobi_wrong.png');
        this.load.image('blank_card',serverIp+'/static/contentsResources/venezia2/blankCard.png');
        this.load.image('explosion',serverIp+'/static/contentsResources/venezia2/explosion-animated.gif');
        this.load.image('result_card',serverIp+'/static/contentsResources/venezia2/'+questionList[questionIndex].english+'_card.png');

        for(var i=0;i<disassembleList.length;i++){
            this.load.image('disassembleImage'+i, serverIp+'/static/contentsResources/venezia2/'+disassembleList[i]+'.png');
        }
    },
    create: function(){
        // start the MainMenu state
        this.state.start('MainMenu');
        console.log('dd')
    }
};
DefaultGame.MainMenu = function(game){}
DefaultGame.MainMenu.prototype = {
    create : function(){
        var scope = angular.element(document.getElementById("contentDIV")).scope();
        //console.log('MainMenu의 ㅊㄱㄷ')
         /*var bgSprite = this.add.sprite(0,0,'background');
         console.log('가로 세로 크기 '+deviceWidth + ','+deviceHeight)
         bgSprite.scale.x = deviceWidth;*/
         //bgSprite.scale.y = deviceHeight;
        //scope.playMusicInAngular('bgm',true);
        this.startGame();
    },
    startGame : function(){
        console.log('메인메뉴의 startGame');
        this.state.start('Game');
    }
}
DefaultGame.Game = function(game){
    // define needed variables for Candy.Game
    console.log("Game initial");
}
DefaultGame.Game.prototype = {
    create: function(){
        bgSprite = this.add.sprite(0,0,'background');
        scaleRatio = 1280 / deviceWidth;
        //sprite.scale.setTo(deviceWidth, deviceHeight);
        bgSprite.width = deviceWidth;
        bgSprite.height = deviceHeight-53;
        game.physics.startSystem(Phaser.Physics.ARCADE);
        //bgSprite.scale.setScreenSize(true);
        //bgSprite.scale.y = 300;
        for(var i=0;i<disassembleList.length;i++){
            this.load.image('disassembleImage'+i, serverIp+'/static/contentsResources/venezia2/'+disassembleList[i]+'.png');

            if(i==0){
                disassembleImageList[i] = this.add.sprite(deviceWidth/3,deviceHeight/3,'disassembleImage'+i);
                disassembleImageList[i].originalPosition = {x:'',y:''};
                disassembleImageList[i].height = disassembleImageList[i].height/scaleRatio;
                disassembleImageList[i].width = disassembleImageList[i].width/scaleRatio;
                disassembleImageList[i].originalPosition.x = disassembleImageList[i].position.x;
                disassembleImageList[i].originalPosition.y = disassembleImageList[i].position.y;
            }else{
                disassembleImageList[i] = this.add.sprite(0,0,'disassembleImage'+i);
                disassembleImageList[i].originalPosition = {x:'',y:''};
                disassembleImageList[i].height = disassembleImageList[i].height/scaleRatio;
                disassembleImageList[i].width = disassembleImageList[i].width/scaleRatio;
                disassembleImageList[i].alignTo(disassembleImageList[i-1], Phaser.RIGHT_CENTER, 20);
                disassembleImageList[i].originalPosition.x = disassembleImageList[i].position.x;
                disassembleImageList[i].originalPosition.y = disassembleImageList[i].position.y;
            }
            console.log('최초의 이미지 위치 '+disassembleImageList[i].originalPosition)
        }
        game.physics.arcade.enable(disassembleImageList);
        setTimeout(function(){

            for(var i=0;i<disassembleImageList.length;i++){
                console.log('처음 움직임을 줄때 disassembleImageList[i]',disassembleImageList[i])
                if(i%2==0){
                    disassembleImageList[i].body.velocity.setTo(100,200);
                }else{
                    disassembleImageList[i].body.velocity.setTo(-100,200);
                }
                disassembleImageList[i].body.bounce.set(1);
                disassembleImageList[i].body.collideWorldBounds = true;
            }

        },1000);

    },
    wrong: function(){
        console.log('틀림ㄴㅇㄹdfsd');
        tobi_wrong = game.add.sprite(deviceWidth/10,deviceHeight/10,'tobi_wrong');
        tobi_wrong.alignIn(bgSprite,Phaser.CENTER_CENTER);
        console.log('틀림ㄴㅇdsfsdfsfㄹsdf');

        setTimeout(function(){
            console.log('tobi_wrong이 제대로 전달되나',tobi_wrong);
            game.add.tween(tobi_wrong).to( { alpha: 0 }, 2000, Phaser.Easing.Linear.none, true);
        },2000)
    },
    right: function(){
        console.log('맞dd음fsdfddddddddddd');
        tobi_right = game.add.sprite(deviceWidth/10,deviceHeight/10,'tobi_right');
        tobi_right.height = tobi_right.height/scaleRatio;
        tobi_right.width = tobi_right.width/scaleRatio;
        tobi_right.alignIn(bgSprite,Phaser.CENTER_CENTER);
        emitter1 = game.add.emitter(game.world.centerX, 0, 100);

        emitter1.makeParticles('flower1');

        emitter1.minParticleSpeed.setTo(-300, 30);
        emitter1.maxParticleSpeed.setTo(300, 100);
        emitter1.minParticleScale = 0.01;
        emitter1.maxParticleScale = 0.1;
        emitter1.gravity = 250;

        //  This will emit a quantity of 5 particles every 500ms. Each particle will live for 2000ms.
        //  The -1 means "run forever"
        emitter1.flow(2000, 500, 5, 50);

        emitter2 = game.add.emitter(game.world.centerX, 0, 100);

        emitter2.makeParticles('flower2');

        emitter2.minParticleSpeed.setTo(-300, 30);
        emitter2.maxParticleSpeed.setTo(300, 100);
        emitter2.minParticleScale = 0.01;
        emitter2.maxParticleScale = 0.1;
        emitter2.gravity = 250;

        //  This will emit a quantity of 5 particles every 500ms. Each particle will live for 2000ms.
        //  The -1 means "run forever"
        emitter2.flow(2000, 500, 5, 50);

        for(var i=0;i<disassembleList.length;i++){
            console.log(disassembleImageList[i].originalPosition);
            disassembleImageList[i].x = disassembleImageList[i].originalPosition.x;
            disassembleImageList[i].y = disassembleImageList[i].originalPosition.y;
            //game.physics.arcade.moveToXY(disassembleImageList[i],disassembleImageList[i].originalPosition.x,disassembleImageList[i].originalPosition.y,60,2000);
            //disassembleImageList[i].body.velocity.setTo(0,0)

            disassembleImageList[i].body.moves = false;
        }
        setTimeout(function(){
            console.log('tobi_right이 제대로 전달되나',tobi_wrong);
            game.add.tween(tobi_right).to( {alpha :0}, 2000, Phaser.Easing.Linear.none, true);
            bgSprite = game.add.sprite(0,0,'background2');
            //sprite.scale.setTo(deviceWidth, deviceHeight);
            bgSprite.width = deviceWidth;
            bgSprite.height = deviceHeight-53;

            var result_card;
            var blank_card;
            var explosion

            blank_card = game.add.sprite(deviceWidth/3,0,'blank_card');
            blank_card.height = blank_card.height/scaleRatio;
            blank_card.width = blank_card.width/scaleRatio;

            game.add.tween(blank_card).to( {alpha :0}, 4000, Phaser.Easing.Linear.none, true);
            setTimeout(function(){
                explosion = game.add.sprite(0,0,'explosion');
                /*explosion.height = blank_card.height*3;
                explosion.width = blank_card.width*3;*/

                explosion.width = deviceWidth;
                explosion.height = deviceHeight-53;

                game.add.tween(explosion).to( {alpha :0}, 2000, Phaser.Easing.Linear.none, true);
            },2000);
            setTimeout(function(){
                console.log('요요요')
                result_card = game.add.sprite(deviceWidth/3,0,'result_card');
                result_card.height = result_card.height/scaleRatio;
                result_card.width = result_card.width/scaleRatio;
                game.add.tween(result_card).to( {alpha :0}, 5000, Phaser.Easing.Linear.none, true);
            },4000);

        },3000)
    }
}

// initialize the framework
var musicList;
var game = new Phaser.Game(deviceWidth, deviceHeight, Phaser.CANVAS, 'hangulbotCotent');

var questionList = [{korean : '개미',english:'ant'},{korean:'나비',english:'butterfly'},{korean:'하마',english:'hippo'},{korean:'사자',english:'lion'}];
var questionIndex = Math.floor(Math.random() * 4);
var question = questionList[questionIndex].korean;
//document.write("<script src='http://192.168.0.146:8888/static/Hangul.js'></script>");
game.crossOrigin = 'anonymous';
//game.load.crossOrigin('anonymous');
console.log('게임이 생성되었나욥~!!!' ,game);
// add game states
var bgSprite;
var scaleRatio;
var disassembleList = Hangul.disassemble(question)
var disassembleImageList = [];
var emitter1;
var emitter2;
var tobi_right;
var tobi_wrong;
var result_card;
var blank_card;
var explosion
game.state.add('Boot', DefaultGame.Boot);
game.state.add('Preloader', DefaultGame.Preloader);
game.state.add('MainMenu', DefaultGame.MainMenu);
game.state.add('Game', DefaultGame.Game);
//game.state.add('Game', DefaultGame.Game);
// start the Boot state
game.state.start('Boot');


// 외부 자바스크립트에서 angularJS에 들어가기 위한 코드


// 효과음 및 비쥐엠 로드 시키는 메서드
function loadingMusic(){
    var scope = angular.element(document.getElementById("contentDIV")).scope();
    console.log('로컬로 유알에 바꿔봄ㅇㅇ')
    //var musicList = [{soundName : 'bgm',soundUrl:'effect/bgm.mp3'}]
    musicList = [
        {soundName : 'bgm',soundUrl:serverIp+'/static/contentsResources/venezia2/bgm.mp3',isExternal:true,isLoaded:false},
        {soundName : 'rightSound',soundUrl:'effect/rightDefault1.mp3',isExternal:false,isLoaded:false},
        {soundName : 'wrongSound',soundUrl:'effect/wrongDefault1.mp3',isExternal:false,isLoaded:false}
    ]
    //var musicList = [{soundName : 'bgm',soundUrl:'effect/bgm.mp3'}]
    musicList = scope.loadingMusicInAngular(musicList);
    console.log('새롭게 로딩된 뮤직 리스트',musicList);
}

function submitAnswer(insertedAnswer){
    var scope = angular.element(document.getElementById("contentDIV")).scope();
    console.log('서버에서 답이 넘어옴 : '+insertedAnswer);
    /*scope.$apply(function() {
     });*/
    if(insertedAnswer=='나비')insertedAnswer=question;
    if(insertedAnswer==question){
        console.log('여기 서버인데 맞는것 왔어ddddd');
        DefaultGame.Game.prototype.right();
        //정답 및 오답 여부를 서버로 전송하기 위한 클라이언트 메서드 실행
        scope.isConRight();
        //클라이언트측 메서드로서 정답 사운드 출력
        scope.playMusicInAngular(musicList,'rightSound');
        //콘텐츠 진행률 100%로 변경해줌
        scope.cotentProgress = 100;
    }else{
        console.log('여기 서버인데 틀린답 왔어x');
        DefaultGame.Game.prototype.wrong();
        //정답 및 오답 여부를 서버로 전송하기 위한 클라이언트 메서드 실행
        scope.isConWrong();
        //클라이언트측 메서드로서 오답 사운드 출력
        scope.playMusicInAngular(musicList,'wrongSound');
    }
}

