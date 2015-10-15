(function () {
    'use strict';
    angular
        .module('tmsAPI')
        .factory('courseAPI', courseAPI);

    /* @ngInject */
    function courseAPI($state, urlProvider) {
        var courseAPI = {
            addLesson: addLesson,
            cancelCreate: cancelCreate,
            cancelEdit: cancelEdit,
            createCourse: createCourse,
            deleteLesson: deleteLesson,
            editCourse: editCourse,
            getAttachments: getAttachments,
            getComments: getComments,
            getCoursesForUser: getCoursesForUser,
            getEditedCourse: getEditedCourse,
            getParticipants: getParticipants,
            getShortInfo: getShortInfo,
            getTimetable: getTimetable,
            editLesson: editLesson
        }
        return courseAPI;

        //////////

        function getAttachments() {

        }

        function getComments() {

        }

        function getParticipants() {

        }

        function getShortInfo() {

        }

        function getTimetable() {

        }

        //////////

        function createCourse(courseData) {
            var fd = new FormData();
            fd.append('courseInfo', JSON.stringify(courseData));

            return $http.post(urlProvider.createCourse(), fd, {
                headers: {
                    'Content-Type': undefined
                },
                transformRequest: angular.identity
            }).then(function (results) {
                console.log('Course created successfully!');
                $state.transitionTo('mycourses');
                return results.data;
            });
        }

        function editCourse(courseId, courseData, isDraft, justEdit) {
            var fd = new FormData();
            if (isDraft) {
                console.log("Course just created");
                if (justEdit) {
                    fd.append('courseInfo', JSON.stringify(courseData));
                    return $http.put(urlProvider.editCourse(courseId), fd, {
                        headers: {
                            'Content-Type': undefined
                        },
                        transformRequest: angular.identity
                    })
                        .then(function (results) {
                            console.log('Course edited successfully!');
                            $state.transitionTo('mycourses');
                            return results.data;
                        });
                }
                else {
                    console.log("admin approves course!");
                    fd.append('courseInfo', JSON.stringify(courseData));
                    return $http.put(urlProvider.confirm(courseId), fd, {
                        headers: {
                            'Content-Type': undefined
                        },
                        transformRequest: angular.identity
                    })
                        .then(function (results) {
                            console.log('Course edited successfully!');
                            $state.transitionTo('mycourses');
                            return results.data;
                        });
                }

            }
            else {
                console.log("isnt draft");
                //courseData.additional = "";
                //courseData.dateTime = [""];
                //courseData.places = [""];

                fd.append('courseInfo', JSON.stringify(courseData));

                if (justEdit) {
                    console.log(courseData);
                    fd.append('courseInfo', JSON.stringify(courseData));
                    return $http.put(urlProvider.editCourse(courseId), fd, {
                        headers: {
                            'Content-Type': undefined
                        },
                        transformRequest: angular.identity
                    })
                        .then(function (results) {
                            console.log('Course edited successfully!');
                            $state.transitionTo('mycourses');
                            return results.data;
                        });
                }
                else {
                    return $http.put(urlProvider.confirm(courseId), fd, {
                        headers: {
                            'Content-Type': undefined
                        },
                        transformRequest: angular.identity
                    })
                        .then(function (results) {
                            console.log('Course approved successfully!');
                            $state.transitionTo('mycourses');
                            return results.data;
                        });

                }
            }
        }

        function cancelCreate(courseId) {
            return $http.put(urlProvider.cancelCreate(courseId), {
                headers: {
                    'Content-Type': undefined
                },
                transformRequest: angular.identity
            }).then(function (results) {
                console.log('Course canceled successfully!');
                $state.transitionTo('mycourses');
                return results.data;
            });
        }

        function cancelEdit(courseId) {
            return $http.put(urlProvider.cancelEdit(courseId), {
                headers: {
                    'Content-Type': undefined
                },
                transformRequest: angular.identity
            }).then(function (results) {
                console.log('Course canceled successfully!');
                $state.transitionTo('mycourses');
                return results.data;
            });
        }

        function getEditedCourse(courseId) {
            return $http.get(urlProvider.getEditedCourse(courseId))
                .then(function (results) {
                    return results.data;
                });
        }

        //////////

        function addLesson(newdDate, newPlace) {

        }

        function editLesson(lessonId, newDate, newPlace) {

        }

        function deleteLesson(lessonId) {

        }

        //////////

        function getCoursesForUser(login) {
            return $http.get(urlProvider.getCoursesForUser(login)).then(function (results) {
                return results.data;
            });
        }

    }

})();