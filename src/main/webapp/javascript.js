'use strict';
angular.module('app', [])

        .controller('searchCntr', ['$scope', '$http', function ($scope, $http) {
                $scope.airlineInfo;
                $scope.departAirport;
                $scope.arrivalAirport;
                $scope.date;
                $scope.tickets;
                
                $scope.departAirport = 'CPH';
                $scope.date = '2017-01-20T00:00:00.000Z';
                $scope.tickets = '1';
                

                $scope.getFlights = function () {
                    $http.get('http://airline-plaul.rhcloud.com/api/flightinfo/'
                            +$scope.departAirport+'/'+$scope.date+'/'+$scope.tickets).then(function (response) {
                        $scope.airlineInfo = response.data;
                    }, function (error) {
                    });
                };
            }])

        .controller('httpController', ['$scope', '$http', function ($scope, $http) {

                $scope.airlineInfo;
                $scope.testRestAir;

                $http.get('http://airline-plaul.rhcloud.com/api/flightinfo/CPH/2017-01-20T00:00:00.000Z/1').then(function (response) {
                    $scope.airlineInfo = response.data;
                }, function (error) {
                });

                $http.get("http://localhost:8080/AF_3sem/api/flights/getflights").then(function (response) {
                    $scope.testRestAir = response.data;
                }, function (error) {
                });


            }])
//        .filter('dateFilter', function () {
//            return function (date) {
//                var dateSplit = date.split("T");
//                return dateSplit[0];
//            };
//        })
//        .filter('timeFilter', function () {
//            return function (date) {
//                var dateSplit = date.split("T");
//                var timeSplit = dateSplit[1].split(":");
//                var time = timeSplit[0] + ":" + timeSplit[1];
//                return time;
//            };
//        })
        .filter('durationFilter', function () {
            return function (time) {
                return (time / 60 | 0) + "h " + time % 60 + "m";
            };
        })
        .filter('arivalFilter', function () {
            return function (time, duration) {
                var timeSplit = time.split(":");
                return (parseInt(timeSplit[0]) + (duration / 60 | 0)) + ":" + (parseInt(timeSplit[1]) + duration % 60);
            };
        });

/*
 .factory('httpFactory', ['$http', function ($http) {
 
 var httpFactory = [];
 
 httpFactory.getFlights = function () {
 var url = 'http://airline-plaul.rhcloud.com/api/flightinfo/CPH/2017-01-20/1';
 console.log(url);
 return $http.get(url);
 };
 
 return httpFactory;
 
 }]);
 */