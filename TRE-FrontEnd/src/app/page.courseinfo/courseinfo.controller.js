(function () {
    'use strict';

    angular
        .module('tmsApp')
        .controller('CourseInfoController', CourseInfoController);

    /** @ngInject */
    function CourseInfoController(courseAPI) {
        var vm = this;

        vm.courseinfo = {};

        vm.getShortInfo = getShortInfo;
        vm.saveAttachments = saveAttachments;
        vm.saveComments = saveComments;
        vm.saveParticipants = saveParticipants;
        vm.saveTimetable = saveTimetable;

        vm.getShortInfo();

        function getShortInfo() {
            //courseAPI.getShortInfo().then(function(data) {
            //    vm.courseinfo.title = data.title;
            //    vm.courseinfo.shortInfo = data.shortInfo;
            //    vm.courseinfo.description = data.description;
            //    vm.courseinfo.coachName = data.coachName;
            //    vm.courseinfo.startDate = data.startDate;
            //    vm.courseinfo.endDate = data.endDate;
            //    vm.courseinfo.language = data.language;
            //    vm.courseinfo.rating = data.rating;
            //    vm.courseinfo.canSubscribe = data.canSubscribe;
            //    vm.courseinfo.canComment = data.canComment;
            //    vm.courseinfo.canRate = data.canRate;
            //    vm.courseinfo.isCoach = data.isCoach;
            //    vm.courseinfo.trainingId = data.trainingId;
            //    vm.courseinfo.coachId = data.coachId;
            //    vm.courseinfo.tagList = angular.copy(data.tagList);
            //});
        }

        function saveAttachments(attachments) {
            vm.courseinfo.attachments = angular.copy(attachments);
        }

        function saveComments(comments) {
            vm.courseinfo.comments = angular.copy(comments);
        }

        function saveParticipants(participants) {
            vm.courseinfo.participants = angular.copy(participants);
        }

        function saveTimetable(timetable) {
            vm.courseinfo.timetable = angular.copy(timetable);
        }

    }
})();