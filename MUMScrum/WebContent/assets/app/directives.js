scrumApp
	.directive('headerMenu',function(){
		return {
			restrict : 'E',
			replace: true,
			templateUrl: 'partials/header_menu.html'
		}
	})
	.directive('productOwnerMenu',function(){
		return {
			restrict: 'E',
			templateUrl: 'partials/product_owner_side_menu.html'
		};
	})
	.directive('scrumMasterMenu',function(){
		return {
			restrict: 'E',
			templateUrl: 'partials/scrum_master_side_menu.html'
		};
	})
	.directive('hrAdminMenu',function(){
		return {
			restrict: 'E',
			templateUrl: 'partials/hr_admin_side_menu.html'
		};
	})
	.directive('developerMenu',function(){
		return {
			restrict: 'E',
			templateUrl: 'partials/developer_menu.html'
		};
	})
	.directive('testerMenu',function(){
		return {
			restrict: 'E',
			templateUrl: 'partials/tester_menu.html'
		};
	})