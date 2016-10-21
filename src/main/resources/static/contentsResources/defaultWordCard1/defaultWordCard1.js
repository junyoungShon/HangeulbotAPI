/**
 * Created by jyson on 2016. 8. 1..
 */

//var serverIp = 'http://hangeulbotelasticbeans-env.ap-northeast-2.elasticbeanstalk.com';
var serverIp = 'http://192.168.0.146:8888';
var deviceWidth = document.getElementById('contentHeight').offsetWidth;
var deviceHeight = (document.getElementById('contentHeight').offsetHeight)-(document.getElementById('contentHeight').offsetHeight/8);
//var deviceHeight = document.body.offsetHeight;

var wordHeight = deviceHeight/2;
var wordWidth =  wordHeight*45/43;
var guideHeight = deviceHeight/2;
var guideWidth = guideHeight*62/43;
var wordX = deviceWidth/8;
var wordY = deviceHeight/4-60;
var guideX = deviceWidth/2.2;
var guideY = deviceHeight/4-60;





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

        //angularJS 로부터 단어 목록을 불러옴
        getQuestionList();
    }
}
DefaultGame.Preloader = function(game){

};
DefaultGame.Preloader.prototype = {
    preload: function(){
        this.stage.backgroundColor = '#B4D9E7';
        this.load.crossOrigin = 'anonymous';
        this.load.image('background', serverIp+'/static/contentsResources/defaultWordCard1/images/bg.png');
        this.load.image('flower1',serverIp+'/static/contentsResources/defaultWordCard1/images/flower1.png');
        this.load.image('flower2',serverIp+'/static/contentsResources/defaultWordCard1/images/flower2.png');
        this.load.image('flower3',serverIp+'/static/contentsResources/defaultWordCard1/images/flower3.png');
        this.load.image('start',serverIp+'/static/contentsResources/defaultWordCard1/images/start.png');
        this.load.image('tobi_right',serverIp+'/static/contentsResources/defaultWordCard1/images/tobi_right.png');
        this.load.image('tobi_wrong',serverIp+'/static/contentsResources/defaultWordCard1/images/tobi_wrong.png');

        for(var i=0;i<questionList.length;i++){
            //단어 리스트 그림 sprite로 변환
            this.load.image(questionList[i].wordId
                , serverIp+'/static/contentsResources/word/word'+questionList[i].wordId+'.png');
            //단어 가이드 그림 sprite로 변환
            this.load.image(questionList[i].wordId+'_guide'
                , serverIp+'/static/contentsResources/guide/guide'+questionList[i].wordId+'.png');
        }
    },
    create: function(){
        // start the MainMenu state
        this.state.start('MainMenu');
    }
};
DefaultGame.MainMenu = function(game){}
DefaultGame.MainMenu.prototype = {
    create : function(){
        this.startGame();
    },
    startGame : function(){
        console.log('메인메뉴의 startGame');
        this.state.start('Game');
    }
}
DefaultGame.Game = function(game){
    console.log("Game initial");
}
DefaultGame.Game.prototype = {
    create: function(){
        //배경 설정
        bgSprite = this.add.sprite(0,0,'background');
        scaleRatio = 1280 / deviceWidth;
        bgSprite.width = deviceWidth;
        bgSprite.height = deviceHeight+5;
        game.physics.startSystem(Phaser.Physics.ARCADE);

        //스타트 이미지 출력
        startSprite = this.add.sprite(deviceWidth/10,10,'start');
        startSprite.alpha = 1;
        //배경음 재생
        playBg();

        for(var i=0;i<questionList.length;i++){
            //단어 리스트 그림 sprite로 변환
            wordSpriteList[i] = this.add.sprite(wordX,wordY,questionList[i].wordId);
            wordSpriteList[i].height = wordHeight;
            wordSpriteList[i].width = wordWidth;
            //단어 가이드 그림 sprite로 변환
            guideSpriteList[i] = this.add.sprite(guideX,-400,questionList[i].wordId+'_guide');
            guideSpriteList[i].height = guideHeight;
            guideSpriteList[i].width = guideWidth;
            //가이드 와 단어 그림 우선 안보이게 설정해 둠
            wordSpriteList[i].alpha = 0;
            guideSpriteList[i].alpha = 0;
        }


        tobi_right = game.add.sprite(deviceWidth/20,deviceHeight/10,'tobi_right');
        tobi_right.alpha = 0;
        tobi_right.height = tobi_right.height/scaleRatio;
        tobi_right.width = tobi_right.width/scaleRatio;
        tobi_right.alignIn(bgSprite,Phaser.CENTER_CENTER);

        tobi_wrong = game.add.sprite(deviceWidth/20,deviceHeight/10,'tobi_wrong');
        tobi_wrong.alpha = 0;
        tobi_wrong.height = tobi_right.height/scaleRatio;
        tobi_wrong.width = tobi_right.width/scaleRatio;
        tobi_wrong.alignIn(bgSprite,Phaser.CENTER_CENTER);

        //시작 시 랑이가 등장해서 게임시작을 알림림
        var startTween = game.add.tween(startSprite).to( { alpha: 0 }, 5000, Phaser.Easing.Linear.None, true);
        startTween.onComplete.add(startNextQuestion, this);


    },
    pause : function(){

    },
    next: function(isCorrect){
        if(isCorrect){
            tobi_right.alpha = 1;
            game.add.tween(tobi_right).to( {alpha :0}, 5000, Phaser.Easing.Linear.none, true);
            congratulation(true);
        }else{
            tobi_wrong.alpha = 1;
            game.add.tween(tobi_wrong).to( { alpha: 0 }, 5000, Phaser.Easing.Linear.none, true);
            congratulation(false);
        }

    }
}

// initialize the framework
var game = new Phaser.Game(deviceWidth, deviceHeight, Phaser.CANVAS, 'hangulbotCotent');

var questionList;
var questionIndex=0;
//var question = questionList[questionIndex].korean;
//document.write("<script src='http://192.168.0.146:8888/static/Hangul.js'></script>");
game.crossOrigin = 'anonymous';
// add game states
var bgSprite;
var wordSpriteList=[];
var guideSpriteList=[];
var startSprite;
var scaleRatio;
var emitter1;
var emitter2;
var emitter3;
var tobi_right;
var tobi_wrong;

var appearGuideSprite ;
var guideActivation1;
var guideActivation2;
var wordActivation1;
var wordActivation2;


var contentStartTime;
var wordStartTime;
var contentEndTime;
var wordEndTime;
var contentStartDate;
var wordStartDate;
var contentEndDate;
var wordEndDate;
var wordTimeRequired;
var contentTimeRequired;
var point=0;

game.state.add('Boot', DefaultGame.Boot);
game.state.add('Preloader', DefaultGame.Preloader);
game.state.add('MainMenu', DefaultGame.MainMenu);
game.state.add('Game', DefaultGame.Game);
//game.state.add('Game', DefaultGame.Game);
// start the Boot state
game.state.start('Boot');




function startNextQuestion(){
    contentStartDate = new Date();
    contentStartTime = new Date().getTime();
    startContent(contentStartDate,contentStartTime);
    apperQuestion();
}
function apperQuestion(){

    wordStartTime = new Date().getTime();
    wordStartDate = new Date();

    if(questionList[questionIndex].guideShown){
        game.add.tween(guideSpriteList[questionIndex]).to( { alpha: 1 }, 2000, Phaser.Easing.Linear.None, true);
        appearGuideSprite = game.add.tween(guideSpriteList[questionIndex]).to( { x:guideX,y: guideY}, 4000, Phaser.Easing.Bounce.Out, true);
    }else{
        game.add.tween(wordSpriteList[questionIndex]).to( { alpha: 1 }, 2000, Phaser.Easing.Linear.None, true);
    }
    game.add.tween(wordSpriteList[questionIndex]).to( { alpha: 1 }, 2000, Phaser.Easing.Linear.None, true);
    activationWord();
}


var playWordSoundInterval;
function activationWord(){
    wordActivation1 = game.add.tween(wordSpriteList[questionIndex]).to( { y: guideY-50 }, 1000, Phaser.Easing.Bounce.Out, true);
    wordActivation1.repeat(-1,200);
    var scope = angular.element(document.getElementById("contentDIV")).scope();

    //가이드 없을 때 단어의 한국어 소리를 재생 해준다.
    if(!questionList[questionIndex].guideShown){
        scope.playWordSound(questionList[questionIndex].wordKorean);
        playWordSoundInterval = setInterval(function(){
            scope.playWordSound(questionList[questionIndex].wordKorean);
        },4000)
    }
}
function congratulation(isCorrect){
    //가이드가 없다면 가이드 출력 후 축하세레머니 이후 없앤다
    if(!questionList[questionIndex].guideShown){
        game.add.tween(guideSpriteList[questionIndex]).to( { alpha: 1 }, 2000, Phaser.Easing.Linear.None, true);
        appearGuideSprite = game.add.tween(guideSpriteList[questionIndex]).to( { x:guideX,y: guideY}, 4000, Phaser.Easing.Bounce.Out, true);
        //소리 반복 삭제
        clearInterval(playWordSoundInterval);
        //4초 지연 후 축하 세레머니
        setTimeout(function(){
            if(isCorrect){
                rightCeremony()
            }else{
                disappearPreviousQuestion();
            }
        },4000);
        //가이드가 있다면 바로 축하세레머니 이후 없앤다
    }else{
        if(isCorrect){
            rightCeremony()
        }else{
            disappearPreviousQuestion();
        }
    }
}

function rightCeremony(){

    //점수 올려줌
    point = point+(100/questionList.length);

    emitter1 = game.add.emitter(game.world.randomX, 0, 1000);
    emitter2 = game.add.emitter(game.world.randomX, 0, 1000);
    emitter3 = game.add.emitter(game.world.randomX, 0, 1000);

    emitter1.makeParticles('flower1');

    emitter1.minParticleSpeed.setTo(-300, 30);
    emitter1.maxParticleSpeed.setTo(300, 100);
    emitter1.minParticleScale = 1;
    emitter1.maxParticleScale = 2;
    emitter1.gravity = 100;

    //  This will emit a quantity of 5 particles every 500ms. Each particle will live for 2000ms.
    //  The -1 means "run forever"
    emitter1.flow(1000, 100, 10, 50);



    emitter2.makeParticles('flower2');

    emitter2.minParticleSpeed.setTo(-300, 30);
    emitter2.maxParticleSpeed.setTo(300, 100);
    emitter2.minParticleScale = 1;
    emitter2.maxParticleScale = 2;
    emitter2.gravity = 250;

    //  This will emit a quantity of 5 particles every 500ms. Each particle will live for 2000ms.
    //  The -1 means "run forever"
    emitter2.flow(1000, 100, 10, 50);


    emitter3.makeParticles('flower3');

    emitter3.minParticleSpeed.setTo(-300, 30);
    emitter3.maxParticleSpeed.setTo(300, 100);
    emitter3.minParticleScale = 1;
    emitter3.maxParticleScale = 2;
    emitter3.gravity = 250;

    //  This will emit a quantity of 5 particles every 500ms. Each particle will live for 2000ms.
    //  The -1 means "run forever"
    emitter3.flow(1000, 100, 10, 50);


    setTimeout(function(){
        disappearPreviousQuestion();
        emitter1.destroy();
        emitter2.destroy();
        emitter3.destroy();
    },4000);
}


function disappearPreviousQuestion(){
    game.add.tween(guideSpriteList[questionIndex]).to( { alpha: 0 }, 2000, Phaser.Easing.Linear.None, true);
    game.add.tween(wordSpriteList[questionIndex]).to( { alpha: 0 }, 2000, Phaser.Easing.Linear.None, true);
    questionIndex++;

    if(questionIndex>=questionList.length){
        finishContent();
    }else{
        setTimeout(function(){apperQuestion();},2000);
    }

}



// 외부 자바스크립트에서 angularJS에 들어가기 위한 코드

// 효과음 및 비쥐엠 로드 시키는 메서드
function playBg(){
    var scope = angular.element(document.getElementById("contentDIV")).scope();
    scope.playBg();
}

//출제될 단어 목록을 받아옴
function getQuestionList(){
    var scope = angular.element(document.getElementById("contentDIV")).scope();
    var data =  scope.getQuestionList();
    for(var i=0;i<data.wordList.length;i++){
        data.wordList[i].isTest = data.isTest[i];
        data.wordList[i].guideShown = data.guideShown[i];
    }
    questionList = data.wordList;
}

function submitAnswer(insertedAnswer){
    var scope = angular.element(document.getElementById("contentDIV")).scope();
    console.log('서버에서 답이 넘어옴 : '+insertedAnswer);

    if(insertedAnswer=='나비')insertedAnswer=questionList[questionIndex].wordKorean;

    wordEndDate = new Date();
    wordEndTime = new Date().getTime();
    wordTimeRequired = (wordEndTime-wordStartTime)/1000;

    if(insertedAnswer==questionList[questionIndex].wordKorean){
        //정답 및 오답 여부를 서버로 전송하기 위한 클라이언트 메서드 실행
        //현재 정답 아이디 , 입력한 정답 , 정답 여부 , 가이드여부,끝낸 시간, 시작 시간 , 소요시간,테스트 여부
        scope.isConRight(questionList[questionIndex].wordId,insertedAnswer,true,questionList[questionIndex].guideShown,
            wordEndDate,wordStartDate,wordTimeRequired,questionList[questionIndex].isTest);
        //클라이언트측 메서드로서 정답 사운드 출력
        scope.playFeedback('right');
        //콘텐츠 진행률 100%로 변경해줌
        console.log('여기 서버인데 맞는것 왔어ddddd');
        DefaultGame.Game.prototype.next(true);
    }else{
        console.log('여기 서버인데 틀린답 왔어x');
        //정답 및 오답 여부를 서버로 전송하기 위한 클라이언트 메서드 실행
        //현재 정답 아이디 , 입력한 정답 , 정답 여부 , 끝낸 시간, 시작 시간 , 소요시간
        scope.isConWrong(questionList[questionIndex].wordId,insertedAnswer,false,questionList[questionIndex].guideShown,
            wordEndDate,wordStartDate,wordTimeRequired,questionList[questionIndex].isTest);
        //클라이언트측 메서드로서 오답 사운드 출력
        scope.playFeedback('wrong');
        DefaultGame.Game.prototype.next(false);
    }
    scope.cotentProgress = Math.round((100/questionList.length)*(questionIndex));
}
function startContent(contentStartDate,contentStartTime){
    var scope = angular.element(document.getElementById("contentDIV")).scope();
    scope.startContent(contentStartDate);
}
function finishContent(){
    var scope = angular.element(document.getElementById("contentDIV")).scope();
    contentEndDate = new Date();
    contentEndTime = new Date().getTime();
    contentTimeRequired = (contentEndTime - contentStartTime)/1000;
    scope.finishContent(contentStartDate,contentEndDate,contentTimeRequired,point,true);
    if(playWordSoundInterval!=null||playWordSoundInterval!=undefined){
        //소리 반복 삭제
        clearInterval(playWordSoundInterval);
    }
}


