/**
 * Created by admin on 21.10.2016.
 */
function showInFrame(frameName, prefix) {
    
    var postfix = '.html';
    var frame1 = document.getElementById('mainFrame');
    frame1.setAttribute('src', prefix + frameName + postfix);
    
}