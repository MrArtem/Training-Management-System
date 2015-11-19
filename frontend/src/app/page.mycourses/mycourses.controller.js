(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('MyCoursesController', MyCoursesController);

    /** @ngInject */
    function MyCoursesController(courseAPI) {
        var vm = this;

        vm.isContentLoaded = false;
        vm.userCourses = [];
        vm.getMyCourses = getMyCourses;
        vm.clearSearch = clearSearch;
        vm.filter = {isCoach: "", title: ""};

        vm.getMyCourses();

        function getMyCourses() {
            courseAPI.getCourseList(true, []).then(function(data) {
                vm.userCourses = angular.copy(data);
                vm.isContentLoaded = true;
                console.log('Received courses: ', vm.userCourses);
            });
        }
        
        function clearSearch() {
            vm.filter = {isCoach: "", title: ""};
        }
        
//        var mock = [
//            {
//                id:"id",
//                title:"Angular Js",
//                excerpt:"Angular Js Materting",
//                coachName:"Anton Dosov",
//                coachId:"1",
//                nextDate:"date",
//                nextPlace:"unit"
//            }
//        ]

    }
})();