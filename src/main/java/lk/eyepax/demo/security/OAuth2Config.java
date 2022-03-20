//package lk.eyepax.demo.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
//@Configuration
//public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
//   private String clientid = "ranga";//"tutorialspoint";
//   private String clientSecret = "my-secret-key";
//   
//   private String privateKey = "MIIEowIBAAKCAQEAyqSzevy87E/SKIRmxFS9UwncL7Tik1Jyi+/bTx5knB1+xLiF\r\n" + 
//   		"M+WSifUqspIUvEx4eMVQqKZbuIJaTjZGDLhzJQ2l7QoBJieJvh7WvEIVzMGcRyP3\r\n" + 
//   		"H+f8AndrRzjo8eDVqyp2d2Dq72HTcCYiqw0MTnDAvDxQCUEjPiFdAtJYeV5uCX/N\r\n" + 
//   		"Hczw1C8pMyO/YWGrtBBAgzsS8ov+j7lEdSw1w8j+IGW9FrOWlmMyQo+uQ+ocoSTg\r\n" + 
//   		"BPprd/zZSj3ok1dwF0A1rXpiOXKNnUKj2nxrqKFe4E9XorQIal135Q9b84HTFICh\r\n" + 
//   		"Lb1JchIhfM0JwAV3saaP4alADps6PfS3jtsNMQIDAQABAoIBAQC2dJUlp2pzW2te\r\n" + 
//   		"D9h37dSJb9dRHQzkmNeKJ+zO0A7GgcgiWAGXOJ04mgZXwi/pWvEdzItTTnZHKxck\r\n" + 
//   		"VLm9ulXyhtdfFiLyt6bOB/ZQizAVrD2J0Fgx4i6GCgANoLAdGNNJynHgCuN9phc6\r\n" + 
//   		"YxvT52utNNhUo/lsW7nftyqH39zJaSEK0sPWjQw0t0I/mMXdWsS+8DGp4PcsCBLr\r\n" + 
//   		"eaFhTu8FdIPRLZvby48AFWh+Vt3+LrX3fAJPxt8EEfMT65I34b81cdWxK1f9YxUz\r\n" + 
//   		"E4MmCPCis84+S4lQtO0jKZHUSnH/IAfjGXwTYcvtPpCKbba51Y8PK/9suIef65+m\r\n" + 
//   		"xslh7QABAoGBAPfgXlbTDOHIRKalH6XonyEpgYg/AosGsvq13sbS99OaEat6qEwa\r\n" + 
//   		"2hTw04jqTb5FT3gekYNcL9EiJ4E6RGQBhSwflTJLgYGizZEKO6WLuhefuMbEPEQt\r\n" + 
//   		"838mr58jhgS2SmdXxCMEvfOWnVPm+cvvjneNiPy8mg26J9H/pRrgtB3xAoGBANFI\r\n" + 
//   		"1idCEdEfDzMvN7hVoi9brnCEjnYYRrG37MKnRT/sMx4lP3ebVODVKQepnSnvAAn3\r\n" + 
//   		"h7otv9lQ5pjeskWqTrHRsaDwrvsRHNqaoAZjQNd0e0M086rvNOJJsGS0XSp+qlct\r\n" + 
//   		"UgPL2kVpBBV1n96vF7m8A/eMqRG8579ZE7Tv/6NBAoGAMkIj3qbcLctVYBSK3jDU\r\n" + 
//   		"Uvz/boiYE2hAUeUeTmHoLddHTeWIyiWHokdSHKilKrcS7eAE0QWYDGdIa0NNKpnE\r\n" + 
//   		"TrX4okH8+DPySj4w6dfy22dfJ64SKiDHBjjp0vJ48eukGmAJPLBxczpOm/xxGZBw\r\n" + 
//   		"6nygEd6LvczI1iNnbpOw8YECgYALtCgNv/shF7yQ3zYdf+R3A4iI0FDrN8ZUT/XU\r\n" + 
//   		"mOgdgAcHy4t2E+C2RhVqUUjxvGTjKc2UUtSkwYpzyBhuHu0RsqldV7hhX/6eFwGV\r\n" + 
//   		"+dz5b5EpG//4cjIh6X8M0q1XIAhEocsxXSgSs6G/1XkgSehowaPzJxGZZVgMcl3Q\r\n" + 
//   		"w48nQQKBgE7TWznRuZ5yO4DTNenb50DE7vYT0zTBQcVhZ2PSHrH4VgrQsrRJJccJ\r\n" + 
//   		"SHCbciKkqhUgZBV+0RwsAflutd43pLg0NANNh5+I9eTsB2y+WZ9+NnocD3PSqVUQ\r\n" + 
//   		"J3Tk2WbDehSnEtCGiLwcbqzw7794vpYhbM73bDBds1zzUkshaVyl";
//   
//   
//   private String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyqSzevy87E/SKIRmxFS9\r\n" + 
//   		"UwncL7Tik1Jyi+/bTx5knB1+xLiFM+WSifUqspIUvEx4eMVQqKZbuIJaTjZGDLhz\r\n" + 
//   		"JQ2l7QoBJieJvh7WvEIVzMGcRyP3H+f8AndrRzjo8eDVqyp2d2Dq72HTcCYiqw0M\r\n" + 
//   		"TnDAvDxQCUEjPiFdAtJYeV5uCX/NHczw1C8pMyO/YWGrtBBAgzsS8ov+j7lEdSw1\r\n" + 
//   		"w8j+IGW9FrOWlmMyQo+uQ+ocoSTgBPprd/zZSj3ok1dwF0A1rXpiOXKNnUKj2nxr\r\n" + 
//   		"qKFe4E9XorQIal135Q9b84HTFIChLb1JchIhfM0JwAV3saaP4alADps6PfS3jtsN\r\n" + 
//   		"MQIDAQAB";
//
//   @Autowired
//   @Qualifier("authenticationManagerBean")
//   private AuthenticationManager authenticationManager;
//   
//   @Bean
//   public JwtAccessTokenConverter tokenEnhancer() {
//      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//      converter.setSigningKey(privateKey);
//      converter.setVerifierKey(publicKey);
//      return converter;
//   }
//   @Bean
//   public JwtTokenStore tokenStore() {
//      return new JwtTokenStore(tokenEnhancer());
//   }
//   @Override
//   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
//      .accessTokenConverter(tokenEnhancer());
//   }
//   @Override
//   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//      security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//   }
//   @Override
//   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//      clients.inMemory().withClient(clientid).secret(clientSecret).scopes("read", "write")
//         .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
//         .refreshTokenValiditySeconds(20000);
//
//   }
//} 