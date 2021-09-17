<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
			// 推送SDK初始化，注意只有用户同意隐私协议后，才能传agreement=1，否则可能APP会被下架
			
            const upush = uni.requireNativePlugin('nrb-upush-plugin')
            // 先注册监听消息透传，Android点击离线通知后才能收到回调
            upush && upush.addCustomMessageListener( res => {
                console.log("upush callback:  res="+JSON.stringify(res))
				if(res.confirm){
				    upush.delayInit({}) 
					uni.showToast({
						icon: 'none',
						title: JSON.stringify(res),
						duration: 3000
					})
				}
            })
            if ('Android' == plus.os.name){      
                //未同意前agreement=0。
                //offlinetoken=1时，通过addCustomMessageListener传递的callbcak回调厂商token
                upush && upush.init({"agreement":"1","offlinetoken":"1"}, res => {
                    console.log("upush init result for Android:  res="+JSON.stringify(res))	
					
					/*uni.showToast({
						icon: 'none',
						title: JSON.stringify(res.device_token),
						duration: 3000
					})*/
					
					uni.showToast({
						icon: 'none',
						title: JSON.stringify(res.device_token),
						duration: 3000
					})
					
					upush.delayInit({})
                                        
                /*uni.showModal({
                    title: '提示',
                    content: '是否同意《隐私政策》',
                    success: res => {
                        // 同意后调用延迟初始化方法
                        console.log(" dres="+JSON.stringify(res))
                        if(res.confirm){
                            upush.delayInit({}) 
							uni.showToast({
								icon: 'none',
								title: JSON.stringify(res),
								duration: 3000
							})
                        }
                    }
                })*/
            }else if('iOS' == plus.os.name){
                uni.showModal({
                    title: '提示',
                    content: '是否同意《隐私政策》',
                    success: res => {
                        // 同意后再调初始化方法
                        console.log(" dres="+JSON.stringify(res))
                        if(res.confirm){
                            upush.init({}, res => {
                                console.log("upush init result for ios:  res="+JSON.stringify(res))	
                            }) 
                        }
                    }
                })
            }
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
	/*每个页面公共css */
</style>
