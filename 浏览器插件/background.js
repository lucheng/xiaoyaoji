chrome.runtime.onMessage.addListener(
    function (request, sender, sendResponse) {
        if(request.contentType == false || request.contentType == 'application/octet-stream'){
            sendResponse({event:'result.complete',data:{
                type:'error',
                readyState:500,
                statusText:'500',
                responseText:'不支持文件上传'
            }});
            return false;
        }
        request.beforeSend = function (xhr) {
            xhr.beginTime = Date.now();
        };
        request.complete = function (xhr, type) {
            var useTime = Date.now() - xhr.beginTime;
            sendResponse({ 
                event:'result.complete',
                data:{
                    type: type,
                    text: (xhr.responseText || xhr.statusText),
                    headers: xhr.getAllResponseHeaders(),
                    readyState: xhr.readyState,
                    responseText: xhr.responseText,
                    status: xhr.status,
                    statusText: xhr.statusText,
                    useTime: useTime
                }
            });
        };
        $.ajax(request);
        return true;
    });
