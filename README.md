# Snack Machine

## Snacks

Un snack est défini par :
- un nom unique
- une description
- un prix
- une quantité dans la machine
- une url vers une image (utiliser https://unsplash.com ou https://picsum.photos/)

Un snack est disponible si sa quantité est strictement supérieure à 0. Il ne peut pas y avoir plus de 20 items du même snack en même temps

## La Machine

La machine est composée de :
- une liste de snacks
- une commande en cours
- le solde du compte

On peut demander à la machine de :
- lister tous les snacks
- Fournir la commande en cours
- Payer la commande
- Fournir le solde du compte
- Ajouter un snack à la commande
- Retirer un snack de la commande

- Quand l'utilisateur paye la commande, la machine doit retirer le montant au solde du compte.
- Si le montant de la commande est supérieure au solde, une erreur est remontée indiquant que le solde est insuffisant.
- Quand l'utilisateur tente d'ajouter un snack indisponible à la commande, une erreur est remontée indiquant que le snack n'est pas disponible.
- Quand l'utilisateur retire un snack qui n'est pas dans la commande, rien ne se passe.
- Quand un utilisateur ajoute un snack à sa commande, le nombre de ce snack disponible doit diminuer d'autant. Mais si la commande échoue, le nombre de snack doit augmenter
- Quand un utilisateur paye sa commande, la machine met entre 3 secondes plus 1 seconde par nombre de snack à s'exécuter et avant de pouvoir accepter de nouvelles commandes.
- On doit pouvoir lister les commandes passées indiquant le solde associé

