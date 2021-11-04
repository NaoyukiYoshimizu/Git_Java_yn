function currentTime(){
    const now=new Date;//dateオブジェクトを生成
    let year=now.getFullYear();//年を取得
    let month=now.getMonth();//月を取得（0~11であることに注意）
    let date=now.getDate();//日を取得
    let hours=now.getHours();//時を取得
    let mins=now.getMinutes();//分を取得
    let secs=now.getSeconds();//秒を取得

    let nowTime=`${year}年${month+1}月${date}日 ${hours}時${mins}分${secs}秒`
    document.getElementById('nowTime').textContent=nowTime;
    refresh();
}

function refresh(){
    setTimeout(currentTime,1000);
}

currentTime();