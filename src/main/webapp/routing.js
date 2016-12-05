'use strict';
angular.module('routingModule', ['ngRoute', 'resultModule'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider
                        .when('/', {
                            templateUrl: 'home.html'
                        })
                        .when('/search', {
                            templateUrl: 'searchview/searchview.html',
                            controller: 'searchCntr'
                        })
                        .when('/reserve', {
                            templateUrl: 'passengerview/passengerview.html',
                            controller: 'searchCntr'
                        })
                        .when('/confirm', {
                            templateUrl: 'confirmationview/confirmationview.html'

                        })
                        .otherwise({
                            redirectTo: '/'
                        });
                        
                        //reloadOnSearch:false   Gør at den ikke reloader data når man skifter view
            }]);



