(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('MyCoursesController', MyCoursesController);

    /** @ngInject */
    function MyCoursesController() {
        var vm = this;
        vm.userCourses = [];
        vm.getMyCourses = getMyCourses;

        vm.filter = {isCoach: "", search: ""};

        vm.userCourses.push({
            trainingTitle: 'Angular Fundamental. Data Binding',
            trainingId: 3,
            nextLesson: 2,
            nextDate: '15.10.2015',
            nextPlace: 'unit 243',
            shortInfo: 'I’ve worked with Angular on quite a few apps at this point, and have seen many different ways to structure them. I’m writing a book on architecting Angular apps right now with the MEAN stack and as such have researched heavily into this specific topic.',
            coachName: 'Papa John',
            coachId: 1,
            isCoach: false
        });

        function getMyCourses(login) {
            courseAPI.getCoursesForUser(login).then(function(data) {
                vm.userCourses = angular.copy(data);
            })
        }

    }
})();