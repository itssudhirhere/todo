function showMessage(msg, type) {
        noty({
            text: msg,
            type: type,
            dismissQueue: true,
            layout      : 'bottomRight',
            theme       : 'defaultTheme',
            timeout     : 5000,
        });
    }