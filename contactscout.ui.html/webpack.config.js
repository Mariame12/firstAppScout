const baseConfig = require('@eclipse-scout/cli/scripts/webpack-defaults');

module.exports = (env, args) => {
  args.resDirArray = ['src/main/resources/WebContent', 'node_modules/@eclipse-scout/core/res'];
  const config = baseConfig(env, args);

  config.entry = {
    'contactscout': './src/main/js/contactscout.js',
    'login': './src/main/js/login.js',
    'logout': './src/main/js/logout.js',
    'contactscout-theme': './src/main/js/contactscout-theme.less',
    'contactscout-theme-dark': './src/main/js/contactscout-theme-dark.less'
  };

  return config;
};
