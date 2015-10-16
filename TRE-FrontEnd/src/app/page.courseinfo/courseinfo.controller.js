(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('CourseInfoController', CourseInfoController);

    /** @ngInject */
    function CourseInfoController(courseAPI) {
        var vm = this;

        vm.courseInfo = {};

        vm.getShortInfo = getShortInfo;
        vm.saveAttachments = saveAttachments;
        vm.saveComments = saveComments;
        vm.saveParticipants = saveParticipants;
        vm.saveTimetable = saveTimetable;

        vm.getShortInfo();

        function getShortInfo() {
            //courseAPI.getShortInfo().then(function(data) {
            //    vm.courseInfo.title = data.title;
            //    vm.courseInfo.shortInfo = data.shortInfo;
            //    vm.courseInfo.description = data.description;
            //    vm.courseInfo.coachName = data.coachName;
            //    vm.courseInfo.startDate = data.startDate;
            //    vm.courseInfo.endDate = data.endDate;
            //    vm.courseInfo.language = data.language;
            //    vm.courseInfo.rating = data.rating;
            //    vm.courseInfo.canSubscribe = data.canSubscribe;
            //    vm.courseInfo.canComment = data.canComment;
            //    vm.courseInfo.canRate = data.canRate;
            //    vm.courseInfo.isCoach = data.isCoach;
            //    vm.courseInfo.trainingId = data.trainingId;
            //    vm.courseInfo.coachId = data.coachId;
            //    vm.courseInfo.tagList = angular.copy(data.tagList);
            //});
        }

        function saveAttachments(attachments) {
            vm.courseInfo.attachments = angular.copy(attachments);
        }

        function saveComments(comments) {
            vm.courseInfo.comments = angular.copy(comments);
        }

        function saveParticipants(participants) {
            vm.courseInfo.participants = angular.copy(participants);
        }

        function saveTimetable(timetable) {
            vm.courseInfo.lessonList = angular.copy(timetable);
        }

    }
})();