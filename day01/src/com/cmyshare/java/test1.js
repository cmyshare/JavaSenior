//裁剪单面辅料明细
let fabricArr=[];
//通过多个sku调用接口得到sku面辅料分组集合
let skuArr=[];
//裁剪单sku明细
let cutSkuArr=[];

function selectionSort(fabricArr,skuArr,cutSkuArr) {
    for (var i = 0; i < fabricArr.length; i++) {
        //取出面辅料id
        var mId=fabricArr[i].mId;
        //sku比对数组
        var skuList=[];
        //取出mId在哪些sku面辅料分组集合中
        for (var j = 0; j < skuArr.length; j++) {
            let maList=skuArr[j].getMaList;
            //比对该sku面辅料数组是否存在该mId
            let reusltMaList=maList.filter(function (item){
                return item.mId2=mId
            })
            //存在加入sku比对数组
            if (reusltMaList.length>0){
                skuList.push(skuArr[j].skuCode);
            }
        }
        //比对出裁剪sku明细哪些sku用了这个mId
        var cutSkuArrResult=cutSkuArr.filter(function (sku){
            return skuList.indexOf(sku.skuCode)>-1;
        })
        //sku结果裁剪数数组列求和
        let cutSum = cutSkuArrResult.reduce((prev, item) => {
            prev += item.cutNum
            return prev
        }, 0)
        fabricArr[i].cutTotal=cutSum;
    }

    //返回比对赋值后，裁剪单面辅料明细
    return fabricArr;
}