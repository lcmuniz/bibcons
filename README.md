Para fazer o deploy da aplicação:
- alterar a versão no application.properties
- fazer o build pelo maven
- logar no registry da aws
  - docker login -p $(aws ecr get-login-password --region sa-east-1 --profile aws_lcmuniz) --username AWS 385624776510.dkr.ecr.sa-east-1.amazonaws.com
- fazer o build da imagem
  - docker build -t bibcons-iesma:8.0.5 .
  - docker tag bibcons-iesma:8.0.5 385624776510.dkr.ecr.sa-east-1.amazonaws.com/bibcons-iesma:8.0.5
  - docker push 385624776510.dkr.ecr.sa-east-1.amazonaws.com/bibcons-iesma:8.0.5
- alterar a imagem no docker-compose.yaml (linode)
- pode ser necessário logar na aws no linode (remover o --profile no comando acima)
- docker-compose up -d