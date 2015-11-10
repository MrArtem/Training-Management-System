(function(){

    "use strict";

    angular
        .module('tmsApp')
        .filter('filterByTags', filterByTags);

    /* @ngInject */
    function filterByTags() {
        return function(input, tagIdList) {
            if(tagIdList.length == 0) {
                return input;
            }

            var filteredArray = [];

            input.forEach(function(course) {
                if(course.tagList.length != 0) {
                    var idList = course.tagList.map(function(tag) {
                        return tag.id;
                    });
                    var found = false;
                    tagIdList.forEach(function(tag) {
                        if(idList.indexOf(tag) > -1) {
                            found = true;
                        }
                    });
                    if(found) {
                        filteredArray.push(course);
                    }
                }
            });

            return filteredArray;
        }
    }


})();