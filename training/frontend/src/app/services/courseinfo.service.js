(function(){
    'use strict';
    angular
        .module('tmsAPI')
        .factory('courseinfo', courseinfo);

    /* @ngInject */
    function courseinfo(){
        var courseinfo = {
            saveTimetable: saveTimetable
        }
        return courseinfo;

        //////////

        function saveTimetable(timetable) {
            courseinfo.timetable = angular.copy(timetable);
        }

    }

})();