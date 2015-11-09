(function(){

    "use strict";

    angular
        .module('tmsApp')
        .filter('filterByTags', filterByTags);

    /* @ngInject */
    function filterByTags() {
        return function(input, tagIdList) {
            if(tagIdList.length == 0) {
                return true;
            }

            var idList = input.tagList.map(function(tag) {
                return tag.id;
            });

            var result = tagIdList.filter(function(tag) {
                return (idList.indexOf(tag) > -1);
            });
            return result.length > 0;
        }
    }


})();