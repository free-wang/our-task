function unique(arr) {
    return arr.filter(function(item,index,arr) {
        return arr.indexOf(item)===index;
    })
}
var arr=[1,1,2,2,2,3,{},{}]
console.log(unique(arr))