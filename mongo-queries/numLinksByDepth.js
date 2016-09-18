/**
 * Created by franciscoalvarez on 18/09/2016.
 */

//links by depth based on num records
db.getCollection('linkDocument').aggregate([
    {
        $match:{
            date: {$gt : ISODate("2016-09-18T13:40:42.147Z")}
        }
    },
    {
        $group:{
            _id: "$depth",
            count: {$sum: 1}
        }
    },
    {
        $sort:{
            _id : 1
        }
    }
]);

//links by depth based on adding child links
//this query appears not to work
db.getCollection('linkDocument').aggregate([
    {
        $match:{
            date: {$gt : ISODate("2016-09-18T13:40:42.147Z")}
        }
    },
    {
        $group:{
            _id: "$depth",
            count: {$sum: "$childLinks"[0].length}
        }
    },
    {
        $sort:{
            _id : 1
        }
    }
]);


//instead we can run this script
var total = 0
db.getCollection('linkDocument').find({"depth":1}).forEach(function(current){
    total += current.childLinks[0].length
})
print("total: " + total)