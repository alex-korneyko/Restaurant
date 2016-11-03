/**
 * Created by admin on 21.10.2016.
 */
function showInFrame(frameNmae, prefix) {

    var frame1 = document.getElementById('mainFrame');
    frame1.setAttribute('src', prefix + frameNmae);
    
}

function showInFrameParentWindow(frameName) {

    var frame = window.parent.document.getElementById('mainFrame');
    frame.setAttribute('src', frameName);
    
}

function selectEmployeePost(post) {
    
    document.getElementById('newEmployeePostDropDown').setAttribute('value', post);
    document.getElementById('employeePost').setAttribute('value', post);
}