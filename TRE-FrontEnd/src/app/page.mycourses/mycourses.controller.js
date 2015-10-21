(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('MyCoursesController', MyCoursesController);

    /** @ngInject */
    function MyCoursesController(authService, courseAPI) {
        var vm = this;

        vm.userCourses = [];
        vm.getMyCourses = getMyCourses;

        vm.filter = {isCoach: "", search: ""};

        vm.getMyCourses();

        //vm.userCourses.push({
        //    trainingTitle: 'Angular Fundamental. Data Binding',
        //    trainingId: 3,
        //    nextLesson: 2,
        //    nextDate: '15.10.2015',
        //    nextPlace: 'unit 243',
        //    shortInfo: 'I\'ve worked with Angular on quite a few apps at this point, and have seen many different ways to structure them. I\'m writing a book on architecting Angular apps right now with the MEAN stack and as such have researched heavily into this specific topic.',
        //    coachName: 'Papa John',
        //    coachId: 1,
        //    isCoach: false
        //});
        //
        //vm.userCourses.push({
        //    trainingTitle: 'Java for beginners',
        //    trainingId: 2,
        //    nextLesson: 1,
        //    nextDate: '18.10.2015',
        //    nextPlace: 'unit 243',
        //    shortInfo: 'Basic Java knowledge.',
        //    coachName: 'Johnny Depp',
        //    coachId: 2,
        //    isCoach: true
        //});

        function getMyCourses() {
            courseAPI.getCoursesForUser(true, []).then(function(data) {
                vm.userCourses = angular.copy(data);
            })
        }

    }
})();